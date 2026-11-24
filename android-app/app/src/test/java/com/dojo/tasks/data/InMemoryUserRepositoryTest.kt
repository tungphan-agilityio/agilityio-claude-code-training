package com.dojo.tasks.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class InMemoryUserRepositoryTest {

    private val repo = InMemoryUserRepository()

    @Test
    fun `register returns the user with a normalized email`() {
        assertEquals(User("nova@dojo.dev"), repo.register("  Nova@Dojo.dev ", "password1"))
    }

    @Test
    fun `registering the same email twice returns null`() {
        repo.register("nova@dojo.dev", "password1")
        assertNull(repo.register("nova@dojo.dev", "password2"))
    }

    @Test
    fun `registering the same email in different case or with spaces returns null`() {
        repo.register("nova@dojo.dev", "password1")
        assertNull(repo.register("  NOVA@dojo.DEV ", "password2"))
    }

    @Test
    fun `authenticate succeeds with the right password`() {
        repo.register("nova@dojo.dev", "password1")
        assertEquals(User("nova@dojo.dev"), repo.authenticate("nova@dojo.dev", "password1"))
    }

    @Test
    fun `authenticate ignores email case and spaces`() {
        repo.register("nova@dojo.dev", "password1")
        assertNotNull(repo.authenticate(" Nova@Dojo.Dev ", "password1"))
    }

    @Test
    fun `authenticate fails with the wrong password`() {
        repo.register("nova@dojo.dev", "password1")
        assertNull(repo.authenticate("nova@dojo.dev", "password2"))
    }

    @Test
    fun `authenticate fails for an unknown email`() {
        assertNull(repo.authenticate("ghost@dojo.dev", "password1"))
    }

    @Test
    fun `stored hash is not the plaintext password`() {
        repo.register("nova@dojo.dev", "password1")
        val stored = repo.storedHash("nova@dojo.dev")!!
        assertFalse(stored.contentEquals("password1".toByteArray(Charsets.UTF_8)))
    }
}
