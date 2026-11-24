package com.dojo.tasks.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AuthRulesTest {

    @Test
    fun `blank email is required`() {
        assertEquals(EmailError.REQUIRED, AuthRules.validateEmail(""))
    }

    @Test
    fun `email of only spaces is required`() {
        assertEquals(EmailError.REQUIRED, AuthRules.validateEmail("   "))
    }

    @Test
    fun `malformed emails are invalid`() {
        listOf("a", "a@", "@b.c", "a@b", "a@.b", "a@b.", "a b@c.d", "a@@b.c", "a@b@c.d").forEach { email ->
            assertEquals("for '$email'", EmailError.INVALID, AuthRules.validateEmail(email))
        }
    }

    @Test
    fun `well formed email is valid`() {
        assertNull(AuthRules.validateEmail("a@b.co"))
    }

    @Test
    fun `email with surrounding spaces and capitals is valid`() {
        assertNull(AuthRules.validateEmail("  A@B.co  "))
    }

    @Test
    fun `normalizing an email trims and lowercases it`() {
        assertEquals("nova@dojo.dev", AuthRules.normalizeEmail("  Nova@Dojo.DEV "))
    }

    @Test
    fun `blank password is too short`() {
        assertEquals(PasswordError.TOO_SHORT, AuthRules.validatePassword(""))
    }

    @Test
    fun `seven character password is too short`() {
        assertEquals(PasswordError.TOO_SHORT, AuthRules.validatePassword("1234567"))
    }

    @Test
    fun `eight character password is valid`() {
        assertNull(AuthRules.validatePassword("12345678"))
    }

    @Test
    fun `spaces count towards password length`() {
        assertNull(AuthRules.validatePassword("  abcd  "))
    }
}
