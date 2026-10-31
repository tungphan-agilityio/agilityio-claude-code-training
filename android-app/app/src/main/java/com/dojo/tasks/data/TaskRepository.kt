package com.dojo.tasks.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

/** Source of truth for tasks. Operations are synchronous so they are easy to unit test. */
interface TaskRepository {
    val tasks: StateFlow<List<Task>>

    fun get(id: Long): Task?

    /** Inserts the task when its id is [Task.NEW_ID], otherwise replaces the task with the same id. */
    fun save(task: Task): Task

    fun delete(id: Long)
}

/** In-memory repository. Data resets every time the app process restarts. */
class InMemoryTaskRepository(initial: List<Task> = emptyList()) : TaskRepository {

    private var nextId = (initial.maxOfOrNull { it.id } ?: 0L) + 1
    private val _tasks = MutableStateFlow(initial)
    override val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    override fun get(id: Long): Task? = _tasks.value.firstOrNull { it.id == id }

    override fun save(task: Task): Task {
        if (task.id == Task.NEW_ID) {
            val created = task.copy(id = nextId++)
            _tasks.update { it + created }
            return created
        }
        _tasks.update { list -> list.map { if (it.id == task.id) task else it } }
        return task
    }

    override fun delete(id: Long) {
        _tasks.update { list -> list.filterNot { it.id == id } }
    }

    companion object {
        /** Demo data shown on first launch, relative to [today] so due dates stay meaningful. */
        fun withSampleData(today: LocalDate = LocalDate.now()) = InMemoryTaskRepository(
            listOf(
                Task(1, "Read the Dojo master plan", "dojo.agilityio.dev", today.minusDays(2), done = true),
                Task(2, "Set up Claude Code Desktop", "Code tab on this repo", today.minusDays(1)),
                Task(3, "Write repro steps for a seeded bug", dueDate = today),
                Task(4, "Draft a PR description with Claude", dueDate = today.plusDays(1)),
                Task(5, "Review a PR with the checklist", "correctness, tests, readability, security, scope"),
            ),
        )
    }
}
