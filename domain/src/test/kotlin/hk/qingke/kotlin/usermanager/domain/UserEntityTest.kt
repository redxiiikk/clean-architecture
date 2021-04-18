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
    fun `should throw username illegal exception given username is less than min length`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("tom", "12345678@Ab", "test@test.hk")
        }
    }

    @Test
    fun `should throw username illegal exception given username is greater than max length`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("tom-test-too-too-long", "12345678@Ab", "test@test.hk")
        }
    }

    @Test
    fun `should throw username illegal exception given username start with number`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("1tom123", "12345678@Ab", "test@test.hk")
        }
    }

    @Test
    fun `should throw username illegal exception given username contain illegal char`() {
        Assertions.assertThrows(UsernameIllegalException::class.java) {
            UserEntity("tom-test@", "12345678@Ab", "test@test.hk")
        }
    }
    // endregion

    // region password exception unit test
    @Test
    fun `should throw password illegal exception given password just contain number`() {
        Assertions.assertThrows(PasswordIllegalException::class.java) {
            UserEntity("tom-test", "12345678", "test@test.hk")
        }
    }

    @Test
    fun `should throw password illegal exception given password is less than min length`() {
        Assertions.assertThrows(PasswordIllegalException::class.java) {
            UserEntity("tom-test", "1234@Ab", "test@test.hk")
        }
    }

    @Test
    fun `should throw password illegal exception given password is greater than max length`() {
        Assertions.assertThrows(PasswordIllegalException::class.java) {
            UserEntity("tom-test", "12345678901234567890@Ab", "test@test.hk")
        }
    }
    // endregion

    // region email exception unit test
    @Test
    fun `should throw password illegal exception given email not contain @`() {
        Assertions.assertThrows(EmailIllegalException::class.java) {
            UserEntity("tom-test-123_test", "12345678@Ab", "test.test.hk")
        }
    }

    @Test
    fun `should throw password illegal exception given email contain illegal char`() {
        Assertions.assertThrows(EmailIllegalException::class.java) {
            UserEntity("tom-test-123_test", "12345678@Ab", "test@test@test.hk")
        }
    }
    // endregion
}