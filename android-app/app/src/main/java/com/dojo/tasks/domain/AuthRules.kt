package com.dojo.tasks.domain

/** Reasons an email can be rejected at sign-up. [TAKEN] comes from the user store, not from [AuthRules]. */
enum class EmailError { REQUIRED, INVALID, TAKEN }

/** Reasons a password can be rejected at sign-up. */
enum class PasswordError { TOO_SHORT }

/** Pure sign-up rules. No Android dependencies (not even `android.util.Patterns`), so they run in JVM tests. */
object AuthRules {

    const val MIN_PASSWORD_LENGTH = 8

    /** Emails are compared trimmed and case-insensitively. */
    fun normalizeEmail(email: String): String = email.trim().lowercase()

    /**
     * Returns null when the email is valid: no whitespace, exactly one `@`, a non-empty part before it,
     * and a part after it containing a `.` that is neither its first nor its last character.
     */
    fun validateEmail(email: String): EmailError? {
        val value = email.trim()
        if (value.isEmpty()) return EmailError.REQUIRED
        val parts = value.split('@')
        if (value.any { it.isWhitespace() } || parts.size != 2) return EmailError.INVALID
        val (local, domain) = parts
        val domainOk = domain.withIndex().any { (i, c) -> c == '.' && i in 1 until domain.lastIndex }
        return if (local.isEmpty() || !domainOk) EmailError.INVALID else null
    }

    /** Returns null when the password is long enough. Passwords are used exactly as typed, spaces included. */
    fun validatePassword(password: String): PasswordError? =
        if (password.length < MIN_PASSWORD_LENGTH) PasswordError.TOO_SHORT else null
}
