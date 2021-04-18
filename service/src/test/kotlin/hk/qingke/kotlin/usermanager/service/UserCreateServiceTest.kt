package hk.qingke.kotlin.usermanager.service

import hk.qingke.kotlin.usermanager.domain.UserEntity
import hk.qingke.kotlin.usermanager.service.adapter.repository.UserRepository
import hk.qingke.kotlin.usermanager.service.exception.EmailDuplicationException
import hk.qingke.kotlin.usermanager.service.exception.UsernameDuplicationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class UserCreateServiceTest {
    private lateinit var userCreateService: UserCreateService
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun init() {
        this.userRepository = mock()

        this.userCreateService = spy(UserCreateService(this.userRepository))
    }

    @Test
    fun `user should be created without exception`() {
        whenever(this.userRepository.queryUserByEmail(any())).thenReturn(null)
        whenever(this.userRepository.queryUserByUsername(any())).thenReturn(null)

        val userEntity = UserEntity("tom-test", "12345678@Ab", "tom@test.hk")
        Assertions.assertDoesNotThrow {
            this.userCreateService.create(userEntity)
        }

        verify(this.userRepository).save(userEntity)
        verify(this.userRepository).queryUserByEmail(userEntity.email)
        verify(this.userRepository).queryUserByUsername(userEntity.username)
    }

    @Test
    fun `should throw username duplication exception`() {
        val userEntity = UserEntity("tom-test", "12345678@Ab", "tom@test.hk")

        whenever(this.userRepository.queryUserByEmail(any())).thenReturn(null)
        whenever(this.userRepository.queryUserByUsername(any())).thenReturn(userEntity)

        Assertions.assertThrows(UsernameDuplicationException::class.java) {
            this.userCreateService.create(userEntity)
        }

        verify(this.userRepository).queryUserByUsername(userEntity.username)
    }

    @Test
    fun `should throw email duplication exception`() {
        val userEntity = UserEntity("tom-test", "12345678@Ab", "tom@test.hk")

        whenever(this.userRepository.queryUserByEmail(any())).thenReturn(userEntity)
        whenever(this.userRepository.queryUserByUsername(any())).thenReturn(null)

        Assertions.assertThrows(EmailDuplicationException::class.java) {
            this.userCreateService.create(userEntity)
        }

        verify(this.userRepository).queryUserByEmail(userEntity.email)
    }
}