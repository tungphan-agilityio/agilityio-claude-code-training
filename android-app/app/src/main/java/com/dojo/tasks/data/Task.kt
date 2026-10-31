package com.dojo.tasks.data

import java.time.LocalDate

/** A single to-do item. An [id] of [NEW_ID] means the task has not been saved yet. */
data class Task(
    val id: Long = NEW_ID,
    val title: String,
    val notes: String = "",
    val dueDate: LocalDate? = null,
    val done: Boolean = false,
) {
    companion object {
        const val NEW_ID = 0L
    }
}
