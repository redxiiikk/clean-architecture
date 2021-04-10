package hk.qingke.learn.usermanager.service;

import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.service.exception.UserEmailDuplicationException;
import hk.qingke.learn.usermanager.service.exception.UsernameDuplicateException;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.Optional;

class UserCreateServiceTest {

    private UserCreateService userCreateService;
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository = Mockito.mock(UserRepository.class);
        userCreateService = new UserCreateService(userRepository);
    }

    @Test
    void create_user_entity() {
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenAnswer((Answer<UserEntity>) invocationOnMock -> {
            UserEntity userEntity = invocationOnMock.getArgument(0, UserEntity.class);
            userEntity.setId(RandomUtils.nextLong());
            return userEntity;
        });


        UserEntity userEntity = new UserEntity("tom", "1@aB12345678", "test@test.hk");
        userEntity = this.userCreateService.create(userEntity);

        Assertions.assertNotNull(userEntity.getId());
    }

    @Test
    void create_user_entity_fail_given_duplicate_name() {
        Mockito.when(this.userRepository.queryByUsername(Mockito.any(String.class))).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertThrows(UsernameDuplicateException.class, () -> {
            UserEntity userEntity = new UserEntity("tom", "1@aB12345678", "test@test.hk");
            this.userCreateService.create(userEntity);
        });
    }

    @Test
    void create_user_entity_fail_given_duplication_email() {
        Mockito.when(this.userRepository.queryByEmail(Mockito.any(String.class))).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertThrows(UserEmailDuplicationException.class, () -> {
            UserEntity userEntity = new UserEntity("tom", "1@aB12345678", "test@test.hk");
            this.userCreateService.create(userEntity);
        });
    }

}