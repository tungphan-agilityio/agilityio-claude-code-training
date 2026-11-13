package com.dojo.tasks.domain

import com.dojo.tasks.data.Task
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class ShareSummaryTest {

    private val today = LocalDate.of(2026, 11, 2)

    @Test
    fun `share text starts with a counts header`() {
        val tasks = listOf(
            Task(1, "Buy milk", dueDate = today),
            Task(2, "Pay rent", dueDate = today.plusDays(2), done = true),
        )
        val text = ShareSummary.buildShareText(tasks, today)
        assertTrue(text.startsWith("2 tasks"))
    }

    @Test
    fun `share text has one line per task with its done mark`() {
        val tasks = listOf(Task(1, "Buy milk", dueDate = today, done = true))
        val text = ShareSummary.buildShareText(tasks, today)
        assertTrue(text.contains("[x] Buy milk"))
    }

    @Test
    fun `suggested subject names the task count`() {
        val tasks = listOf(Task(1, "a"), Task(2, "b"))
        assertEquals("Dojo Tasks (2)", ShareSummary.suggestedSubject(tasks))
    }

    @Test
    fun `suggested file name includes the first task title and date`() {
        val tasks = listOf(Task(1, "Groceries"))
        assertEquals("dojo-tasks-Groceries-2026-11-02.txt", ShareSummary.suggestedFileName(tasks, today))
    }

    @Test
    fun `suggested file name falls back when list is empty`() {
        assertEquals("dojo-tasks-tasks-2026-11-02.txt", ShareSummary.suggestedFileName(emptyList(), today))
    }
}
