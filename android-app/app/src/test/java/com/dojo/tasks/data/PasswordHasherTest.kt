package com.dojo.tasks.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PasswordHasherTest {

    private val salt = PasswordHasher.newSalt()

    @Test
    fun `same password and salt match`() {
        val hash = PasswordHasher.hash("correct horse", salt)
        assertTrue(PasswordHasher.matches("correct horse", salt, hash))
    }

    @Test
    fun `wrong password does not match`() {
        val hash = PasswordHasher.hash("correct horse", salt)
        assertFalse(PasswordHasher.matches("correct horsf", salt, hash))
    }

    @Test
    fun `different salts give different hashes for the same password`() {
        val other = PasswordHasher.newSalt()
        assertFalse(PasswordHasher.hash("correct horse", salt).contentEquals(PasswordHasher.hash("correct horse", other)))
    }

    @Test
    fun `salt is 16 bytes`() {
        assertEquals(16, salt.size)
    }
}
