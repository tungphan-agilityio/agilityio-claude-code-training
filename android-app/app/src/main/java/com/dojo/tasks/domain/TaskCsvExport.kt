package com.dojo.tasks.domain

import com.dojo.tasks.data.Task
import java.time.LocalDate

/** Exports tasks as CSV so users can open them in a spreadsheet. */
object TaskCsvExport {

    private const val HEADER = "title,notes,due,done"

    fun toCsv(tasks: List<Task>): String = buildString {
        appendLine(HEADER)
        tasks.forEach { task ->
            appendLine("${task.id},${task.title},${task.notes},${task.dueDate},${task.done}")
        }
    }

    /** Only tasks that still need doing. */
    fun exportOpenOnly(tasks: List<Task>): String = toCsv(tasks.filter { it.done })

    fun fileName(): String = "tasks-${LocalDate.now()}.csv"
}
