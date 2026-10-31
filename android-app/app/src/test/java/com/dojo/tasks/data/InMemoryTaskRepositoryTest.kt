package com.dojo.tasks.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class InMemoryTaskRepositoryTest {

    @Test
    fun `saving a new task assigns the next id`() {
        val repo = InMemoryTaskRepository(listOf(Task(7, "existing")))
        val saved = repo.save(Task(title = "new"))
        assertEquals(8L, saved.id)
        assertEquals(2, repo.tasks.value.size)
    }

    @Test
    fun `saving an existing task replaces it`() {
        val repo = InMemoryTaskRepository(listOf(Task(1, "old")))
        repo.save(Task(1, "renamed"))
        assertEquals(listOf(Task(1, "renamed")), repo.tasks.value)
    }

    @Test
    fun `delete removes the task`() {
        val repo = InMemoryTaskRepository(listOf(Task(1, "a"), Task(2, "b")))
        repo.delete(1)
        assertNull(repo.get(1))
        assertEquals(1, repo.tasks.value.size)
    }

    @Test
    fun `sample data has five tasks`() {
        assertEquals(5, InMemoryTaskRepository.withSampleData().tasks.value.size)
    }
}
