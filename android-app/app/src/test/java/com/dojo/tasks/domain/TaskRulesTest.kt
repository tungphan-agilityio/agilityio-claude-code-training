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

    // Issue #3: a task due today shows as Overdue, but the user is not late yet.
    @Test
    fun `task due today is not overdue`() {
        assertFalse(TaskRules.isOverdue(Task(title = "t", dueDate = today), today))
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
    fun `title of only whitespace is required`() {
        assertEquals(TitleError.REQUIRED, TaskRules.validateTitle("   "))
        assertEquals(TitleError.REQUIRED, TaskRules.validateTitle("\t"))
        assertEquals(TitleError.REQUIRED, TaskRules.validateTitle("\n  "))
        assertEquals(TitleError.REQUIRED, TaskRules.validateTitle(" \t\n "))
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
    fun `overdue count counts only open tasks due before today`() {
        val tasks = listOf(
            Task(1, "past open", dueDate = today.minusDays(1)),
            Task(2, "past done", dueDate = today.minusDays(1), done = true),
            Task(3, "due today", dueDate = today),
            Task(4, "undated"),
            Task(5, "past open too", dueDate = today.minusDays(3)),
        )
        assertEquals(2, TaskRules.overdueCount(tasks, today))
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

    // Issue #6: search matches titles only, ignoring case and surrounding spaces.
    private val milk = Task(1, "Buy Milk", notes = "whole, 2 litres")
    private val tea = Task(2, "Make tea")

    @Test
    fun `title search ignores case`() {
        assertEquals(listOf(milk), TaskRules.filterByTitle(listOf(milk, tea), "milk"))
    }

    @Test
    fun `title search matches part of a title and trims the query`() {
        assertEquals(listOf(tea), TaskRules.filterByTitle(listOf(milk, tea), "  tea "))
    }

    @Test
    fun `blank query keeps every task`() {
        assertEquals(listOf(milk, tea), TaskRules.filterByTitle(listOf(milk, tea), "   "))
    }

    @Test
    fun `title search does not match notes`() {
        assertEquals(emptyList<Task>(), TaskRules.filterByTitle(listOf(milk, tea), "litres"))
    }
}
