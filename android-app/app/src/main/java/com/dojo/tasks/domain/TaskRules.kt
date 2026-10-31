package com.dojo.tasks.domain

import com.dojo.tasks.data.Task
import java.time.LocalDate
import java.time.format.DateTimeParseException

/** Reasons a task title can be rejected by [TaskRules.validateTitle]. */
enum class TitleError { REQUIRED, TOO_LONG }

/** Pure business rules for tasks. No Android dependencies, so everything here is unit-testable. */
object TaskRules {

    const val MAX_TITLE_LENGTH = 80

    /** A task is overdue when it is not done and its due date is before [today]. */
    fun isOverdue(task: Task, today: LocalDate): Boolean {
        val due = task.dueDate ?: return false
        return !task.done && !due.isAfter(today)
    }

    /** Returns null when the title is valid. */
    fun validateTitle(title: String): TitleError? = when {
        title.isEmpty() -> TitleError.REQUIRED
        title.trim().length > MAX_TITLE_LENGTH -> TitleError.TOO_LONG
        else -> null
    }

    /** Parses an optional yyyy-mm-dd date. Blank input means "no due date". */
    fun parseDueDate(input: String): Result<LocalDate?> {
        if (input.isBlank()) return Result.success(null)
        return try {
            Result.success(LocalDate.parse(input.trim()))
        } catch (e: DateTimeParseException) {
            Result.failure(e)
        }
    }

    /** Number of tasks that still need doing. */
    fun remainingCount(tasks: List<Task>): Int = tasks.count { !it.done }

    /** Open tasks first, ordered by due date (no date last), then completed tasks. */
    fun sortForList(tasks: List<Task>): List<Task> =
        tasks.sortedWith(
            compareBy<Task> { it.done }
                .thenBy { it.dueDate == null }
                .thenBy { it.dueDate }
                .thenBy { it.id },
        )
}
