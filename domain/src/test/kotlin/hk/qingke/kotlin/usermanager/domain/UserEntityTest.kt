package hk.qingke.kotlin.usermanager.domain

import hk.qingke.kotlin.usermanager.domain.exception.EmailIllegalException
import hk.qingke.kotlin.usermanager.domain.exception.PasswordIllegalException
import hk.qingke.kotlin.usermanager.domain.exception.UsernameIllegalException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class UserEntityTest {
    @Test
    fun `user entity should be created without exception`() {
        Assertions.assertDoesNotThrow {
            UserEntity("tom-test-123_test", "12345678@Ab", "test@test.hk")
        }
    }

    // region username exception unit test

    @Test
    fun `username length is less than the minimum length`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("tom", "12345678@Ab", "test@test.hk")
        }
    }

    @Test
    fun `username length should be greater than the maximum length`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("tom-test-too-too-long", "12345678@Ab", "test@test.hk")
        }
    }

    @Test
    fun `username start with number`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("1tom123", "12345678@Ab", "test@test.hk")
        }
    }

    @Test
    fun `username contain illegal char`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("tom-test@", "12345678@Ab", "test@test.hk")
        }
    }
    // endregion

    // region password exception unit test
    @Test
    fun `password just contain number`() {
        Assertions.assertThrows(PasswordIllegalException::class.java) {
            UserEntity("tom-test", "12345678", "test@test.hk")
        }
    }

    @Test
    fun `password length is less than the minimum length`() {
        Assertions.assertThrows(PasswordIllegalException::class.java) {
            UserEntity("tom-test", "1234@Ab", "test@test.hk")
        }
    }

    @Test
    fun `password length should be greater than the maximum length`() {
        Assertions.assertThrows(PasswordIllegalException::class.java) {
            UserEntity("tom-test", "12345678901234567890@Ab", "test@test.hk")
        }
    }
    // endregion

    // region email exception unit test
    @Test
    fun `email not contain @`() {
        Assertions.assertThrows(EmailIllegalException::class.java) {
            UserEntity("tom-test-123_test", "12345678@Ab", "test.test.hk")
        }
    }

    @Test
    fun `email contain illegal char`() {
        Assertions.assertThrows(EmailIllegalException::class.java) {
            UserEntity("tom-test-123_test", "12345678@Ab", "test@test@test.hk")
        }
    }
    // endregion
}