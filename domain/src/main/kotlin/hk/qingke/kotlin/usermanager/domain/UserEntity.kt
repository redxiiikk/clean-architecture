package hk.qingke.kotlin.usermanager.domain

import hk.qingke.kotlin.usermanager.domain.exception.EmailIllegalException
import hk.qingke.kotlin.usermanager.domain.exception.PasswordIllegalException
import hk.qingke.kotlin.usermanager.domain.exception.UsernameIllegalException

data class UserEntity(
    val id: Long?,
    val username: String,
    val password: String,
    val email: String
) {
    companion object {
        private val USERNAME_REGEX = Regex("(?![0-9])[a-zA-Z0-9\\-_]{5,20}")
        private val PASSWORD_REGEX = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
        private val EMAIL_REGEX = Regex("[\\w.-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}")
    }

    init {
        if (!USERNAME_REGEX.matches(username)) {
            throw UsernameIllegalException()
        }

        if (!PASSWORD_REGEX.matches(password)) {
            throw PasswordIllegalException()
        }

        if (!EMAIL_REGEX.matches(email)) {
            throw EmailIllegalException()
        }
    }

    constructor(username: String, password: String, email: String) : this(null, username, password, email)
}
