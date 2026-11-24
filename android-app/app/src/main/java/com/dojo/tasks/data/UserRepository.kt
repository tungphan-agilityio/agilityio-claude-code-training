package com.dojo.tasks.data

import com.dojo.tasks.domain.AuthRules

/** Store of user accounts. Operations are synchronous so they are easy to unit test. */
interface UserRepository {
    /** Creates an account. Returns null when the email (after normalizing) is already registered. */
    fun register(email: String, password: String): User?

    /** Returns the user when the email and password match an account, otherwise null. */
    fun authenticate(email: String, password: String): User?
}

/** In-memory accounts keyed by normalized email. Data resets every time the app process restarts. */
class InMemoryUserRepository : UserRepository {

    /** Only the salt and hash are kept; the plaintext password is never stored. */
    private class Account(val salt: ByteArray, val hash: ByteArray)

    private val accounts = mutableMapOf<String, Account>()

    override fun register(email: String, password: String): User? {
        val key = AuthRules.normalizeEmail(email)
        if (key in accounts) return null
        val salt = PasswordHasher.newSalt()
        accounts[key] = Account(salt, PasswordHasher.hash(password, salt))
        return User(key)
    }

    override fun authenticate(email: String, password: String): User? {
        val key = AuthRules.normalizeEmail(email)
        val account = accounts[key] ?: return null
        return if (PasswordHasher.matches(password, account.salt, account.hash)) User(key) else null
    }

    /** Visible for tests: the stored hash for [email], so tests can check no plaintext is kept. */
    internal fun storedHash(email: String): ByteArray? = accounts[AuthRules.normalizeEmail(email)]?.hash
}
