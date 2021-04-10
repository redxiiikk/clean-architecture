package hk.qingke.learn.usermanager.infrastructure.repository;


import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.infrastructure.mapper.UserMapper;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class UserRepositoryTest {
    private UserMapper userMapper;
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        this.userRepository = new UserRepositoryImpl(this.userMapper);
    }

    @Test
    void query_user_by_email() {
        Optional<UserEntity> userEntity = this.userRepository.queryByEmail("test@test.hk");

        Assertions.assertTrue(userEntity.isPresent());
    }

    @Test
    void query_user_by_email_fail_given_not_exit_email() {
        Optional<UserEntity> userEntity = this.userRepository.queryByEmail("test-not-exit@test.hk");

        Assertions.assertTrue(userEntity.isEmpty());
    }
}