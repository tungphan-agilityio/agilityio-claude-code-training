package com.dojo.tasks.domain

import com.dojo.tasks.data.Task
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class TaskRulesTest {

    private val today = LocalDate.of(2026, 11, 2)

    @Test
    fun `task due yesterday and not done is overdue`() {
        assertTrue(TaskRules.isOverdue(Task(title = "t", dueDate = today.minusDays(1)), today))
    }

    @Test
    fun `task due tomorrow is not overdue`() {
        assertFalse(TaskRules.isOverdue(Task(title = "t", dueDate = today.plusDays(1)), today))
    }

    @Test
    fun `done task is never overdue`() {
        assertFalse(TaskRules.isOverdue(Task(title = "t", dueDate = today.minusDays(5), done = true), today))
    }

    @Test
    fun `task without due date is not overdue`() {
        assertFalse(TaskRules.isOverdue(Task(title = "t"), today))
    }

    @Test
    fun `empty title is required`() {
        assertEquals(TitleError.REQUIRED, TaskRules.validateTitle(""))
    }

    @Test
    fun `title over max length is too long`() {
        assertEquals(TitleError.TOO_LONG, TaskRules.validateTitle("x".repeat(TaskRules.MAX_TITLE_LENGTH + 1)))
    }

    @Test
    fun `normal title is valid`() {
        assertNull(TaskRules.validateTitle("Buy milk"))
    }

    @Test
    fun `blank due date parses to null`() {
        assertNull(TaskRules.parseDueDate("  ").getOrThrow())
    }

    @Test
    fun `iso due date parses`() {
        assertEquals(LocalDate.of(2026, 12, 1), TaskRules.parseDueDate("2026-12-01").getOrThrow())
    }

    @Test
    fun `bad due date fails`() {
        assertTrue(TaskRules.parseDueDate("01/12/2026").isFailure)
    }

    @Test
    fun `remaining count ignores done tasks`() {
        val tasks = listOf(Task(1, "a"), Task(2, "b", done = true), Task(3, "c"))
        assertEquals(2, TaskRules.remainingCount(tasks))
    }

    @Test
    fun `sort puts open tasks by due date first, undated next, done last`() {
        val done = Task(1, "done", dueDate = today.minusDays(3), done = true)
        val undated = Task(2, "undated")
        val later = Task(3, "later", dueDate = today.plusDays(3))
        val sooner = Task(4, "sooner", dueDate = today)
        assertEquals(listOf(sooner, later, undated, done), TaskRules.sortForList(listOf(done, undated, later, sooner)))
    }
}
