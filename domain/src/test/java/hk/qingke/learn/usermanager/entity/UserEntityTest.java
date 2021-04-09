package hk.qingke.learn.usermanager.entity;

import hk.qingke.learn.usermanager.entity.exception.PasswordIllegalException;
import hk.qingke.learn.usermanager.entity.exception.UsernameIsBlankException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UserEntityTest {
    @Test
    void create_user_entity_success() {
        UserEntity userEntity = new UserEntity("Tom", "1@Aa1234567", "test@test.hk");
        Assertions.assertNotNull(userEntity);
    }

    @Test
    void create_user_entity_fail_given_blank_username() {
        Assertions.assertThrows(
                UsernameIsBlankException.class,
                () -> new UserEntity("", "1@Aa1234567", "test@test.hk"));
    }

    @Test
    void create_user_entity_fail_given_valid_password() {
        List<String> illegalPasswords = Arrays.asList(
                "12345678",
                "abcdefgh",
                "ABCDEFGH",
                "@@@@@@@@",
                "1@ab",
                "1@ab cde"
        );

        for (String illegalPassword : illegalPasswords) {
            Assertions.assertThrows(
                    PasswordIllegalException.class,
                    () -> new UserEntity("Tom", illegalPassword, "test@test.hk"));
        }
    }
}