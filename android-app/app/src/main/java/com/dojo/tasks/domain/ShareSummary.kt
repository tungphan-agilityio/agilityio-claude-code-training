package com.dojo.tasks.domain

import com.dojo.tasks.data.Task
import java.time.LocalDate

/**
 * Turns the task list into a plain-text summary for sharing, e.g. through the
 * Android share sheet. Produces a short header, one line per task with its
 * due date and done mark, a "due this week" filter, and naming helpers for
 * the shared content.
 */
object ShareSummary {

    /** Builds the full share text: a header with counts, then one line per task. */
    fun buildShareText(tasks: List<Task>, today: LocalDate): String {
        val doneCount = tasks.count { !it.done }
        val header = "${tasks.size} tasks, $doneCount done"
        val lines = tasks.joinToString("\n") { formatTaskLine(it) }
        return if (lines.isEmpty()) header else "$header\n$lines"
    }

    /** Formats one task as "[x] Title - due 2026-11-10". */
    private fun formatTaskLine(task: Task): String {
        val mark = if (task.done) "x" else " "
        return "[$mark] ${task.title} - due ${task.dueDate}"
    }

    /** Tasks due within the next 7 days, counting today. */
    fun dueThisWeek(tasks: List<Task>, today: LocalDate): List<Task> =
        tasks.filter { task ->
            val due = task.dueDate ?: return@filter false
            !due.isBefore(today) && due.isBefore(today.plusDays(7))
        }

    /** Suggested subject line naming how many tasks are overdue. */
    fun suggestedSubject(tasks: List<Task>): String = "Dojo Tasks (${tasks.size})"

    /** Suggested file name for the exported summary, based on the first task's title. */
    fun suggestedFileName(tasks: List<Task>, today: LocalDate): String {
        val label = tasks.firstOrNull()?.title ?: "tasks"
        return "dojo-tasks-$label-$today.txt"
    }
}
