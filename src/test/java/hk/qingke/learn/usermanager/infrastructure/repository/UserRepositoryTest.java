package hk.qingke.learn.usermanager.infrastructure.repository;


import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.infrastructure.mapper.UserMapper;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

@Rollback
@MybatisTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:database/user-repository-test.sql")
class UserRepositoryTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserMapper userMapper;

    private UserRepository userRepository;

    @BeforeEach
    void init() {
        this.userRepository = new UserRepositoryImpl(this.userMapper);
    }

    @Test
    void query_user_by_email() {
        Optional<UserEntity> userEntity = this.userRepository.queryByEmail("test-tom@test.hk");

        LOGGER.info(userEntity.toString());

        Assertions.assertTrue(userEntity.isPresent());
    }

    @Test
    void query_user_by_email_fail_given_not_exit_email() {
        Optional<UserEntity> userEntity = this.userRepository.queryByEmail("test-not-exit@test.hk");

        LOGGER.info(userEntity.toString());

        Assertions.assertTrue(userEntity.isEmpty());
    }
}