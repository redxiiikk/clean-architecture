package hk.qingke.learn.usermanager.infrastructure.repository;


import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.infrastructure.dos.UserDo;
import hk.qingke.learn.usermanager.infrastructure.mapper.UserMapper;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@Slf4j
@Rollback
@MybatisTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:database/user-repository-test.sql")
class UserRepositoryTest {

    @SpyBean
    private UserMapper userMapper;

    private UserRepository userRepository;

    @BeforeEach
    void init() {
        this.userMapper = Mockito.spy(this.userMapper);

        this.userRepository = new UserRepositoryImpl(this.userMapper);
    }

    @Test
    void query_user_by_email() {
        Optional<UserEntity> userEntity = this.userRepository.queryByEmail("test-tom@test.hk");

        log.info(userEntity.toString());

        Assertions.assertTrue(userEntity.isPresent());
    }

    @Test
    void query_user_by_email_fail_given_not_exit_email() {
        Optional<UserEntity> userEntity = this.userRepository.queryByEmail("test-not-exit@test.hk");

        log.info(userEntity.toString());

        Assertions.assertTrue(userEntity.isEmpty());
    }

    @Test
    void query_user_by_username() {
        Optional<UserEntity> userEntity = this.userRepository.queryByUsername("Tom");

        log.info(userEntity.toString());

        Assertions.assertTrue(userEntity.isPresent());
    }

    @Test
    void query_user_by_username_given_not_exit_name() {
        Optional<UserEntity> userEntity = this.userRepository.queryByUsername("Tom-not-exit");

        log.info(userEntity.toString());

        Assertions.assertTrue(userEntity.isEmpty());
    }

    @Test
    @Rollback
    void update_user_when_call_save_method_given_exit_user() {
        Optional<UserEntity> userEntity = this.userRepository.queryByUsername("Tom");

        userEntity.ifPresentOrElse(it -> {
            it.setEmail("tom-update@test.hk");

            this.userRepository.save(it);

            ArgumentCaptor<UserDo> argumentCaptor = ArgumentCaptor.forClass(UserDo.class);
            Mockito.verify(this.userMapper, Mockito.times(1)).updateById(argumentCaptor.capture());

            UserDo userDo = argumentCaptor.getValue();
            Assertions.assertEquals(it.getId(), userDo.getId());
            Assertions.assertEquals(it.getUsername(), userDo.getUsername());
            Assertions.assertEquals(it.getEmail(), userDo.getEmail());

            Mockito.verify(this.userMapper, Mockito.never()).create(Mockito.any(UserDo.class));
        }, Assertions::fail);
    }

    @Test
    @Rollback
    void create_user_when_call_save_method_given_not_exit_user() {
        UserEntity userEntity = new UserEntity("tom-new", "12345678@Ab", "tom-new@test.hk");

        userEntity = this.userRepository.save(userEntity);

        log.info(userEntity.toString());

        Assertions.assertNotNull(userEntity.getId());

        Mockito.verify(this.userMapper, Mockito.never()).updateById(Mockito.any(UserDo.class));

        ArgumentCaptor<UserDo> argumentCaptor = ArgumentCaptor.forClass(UserDo.class);
        Mockito.verify(this.userMapper, Mockito.times(1)).create(argumentCaptor.capture());

        UserDo userDo = argumentCaptor.getValue();
        Assertions.assertEquals(userEntity.getUsername(), userDo.getUsername());
        Assertions.assertEquals(userEntity.getPassword(), userDo.getPassword());
        Assertions.assertEquals(userEntity.getEmail(), userDo.getEmail());
    }
}