package com.dojo.tasks.ui

import androidx.lifecycle.ViewModel
import com.dojo.tasks.data.Task
import com.dojo.tasks.data.TaskRepository
import com.dojo.tasks.domain.TaskRules
import com.dojo.tasks.domain.TitleError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Which screen is showing. Kept in the ViewModel so navigation is unit-testable. */
sealed interface Screen {
    data object List : Screen
    data class Detail(val taskId: Long) : Screen

    /** [taskId] is null when adding a new task. */
    data class Form(val taskId: Long? = null) : Screen
}

/** Validation errors for the add/edit form. Null fields mean "no error". */
data class FormErrors(val title: TitleError? = null, val badDate: Boolean = false) {
    val hasErrors get() = title != null || badDate
}

class TasksViewModel(private val repository: TaskRepository) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.tasks

    private val _screen = MutableStateFlow<Screen>(Screen.List)
    val screen: StateFlow<Screen> = _screen.asStateFlow()

    private val _formErrors = MutableStateFlow(FormErrors())
    val formErrors: StateFlow<FormErrors> = _formErrors.asStateFlow()

    fun task(id: Long): Task? = repository.get(id)

    fun openDetail(id: Long) = navigate(Screen.Detail(id))

    fun openNewTask() = navigate(Screen.Form())

    fun openEdit(id: Long) = navigate(Screen.Form(id))

    /** Handles system back and the toolbar back button. Returns false when already on the list. */
    fun back(): Boolean {
        val target = when (val current = _screen.value) {
            Screen.List -> return false
            is Screen.Detail -> Screen.List
            is Screen.Form -> current.taskId?.let { Screen.Detail(it) } ?: Screen.List
        }
        navigate(target)
        return true
    }

    fun toggleDone(id: Long) {
        val task = repository.get(id) ?: return
        repository.save(task.copy(done = !task.done))
    }

    fun delete(id: Long) {
        repository.delete(id)
        navigate(Screen.List)
    }

    /** Validates and saves the form, then shows the saved task. Returns false when the input is invalid. */
    fun saveForm(title: String, notes: String, dueDateText: String): Boolean {
        val due = TaskRules.parseDueDate(dueDateText)
        val errors = FormErrors(title = TaskRules.validateTitle(title), badDate = due.isFailure)
        _formErrors.value = errors
        if (errors.hasErrors) return false

        val editing = (_screen.value as? Screen.Form)?.taskId?.let(repository::get)
        val saved = repository.save(
            Task(
                title = title.trim(),
                notes = notes.trim(),
                dueDate = due.getOrNull(),
                done = editing?.done ?: false,
            ),
        )
        navigate(Screen.Detail(saved.id))
        return true
    }

    private fun navigate(target: Screen) {
        _formErrors.value = FormErrors()
        _screen.value = target
    }
}
