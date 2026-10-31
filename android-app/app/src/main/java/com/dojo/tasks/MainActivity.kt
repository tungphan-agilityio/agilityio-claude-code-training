package com.dojo.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dojo.tasks.data.InMemoryTaskRepository
import com.dojo.tasks.ui.Screen
import com.dojo.tasks.ui.TaskDetailScreen
import com.dojo.tasks.ui.TaskFormScreen
import com.dojo.tasks.ui.TaskListScreen
import com.dojo.tasks.ui.TasksViewModel
import com.dojo.tasks.ui.theme.DojoTasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DojoTasksTheme {
                DojoTasksApp()
            }
        }
    }
}

@Composable
private fun DojoTasksApp(
    vm: TasksViewModel = viewModel { TasksViewModel(InMemoryTaskRepository.withSampleData()) },
) {
    val screen by vm.screen.collectAsStateWithLifecycle()
    val tasks by vm.tasks.collectAsStateWithLifecycle()
    val formErrors by vm.formErrors.collectAsStateWithLifecycle()

    BackHandler(enabled = screen != Screen.List) { vm.back() }

    when (val current = screen) {
        Screen.List -> TaskListScreen(
            tasks = tasks,
            onOpen = vm::openDetail,
            onToggleDone = vm::toggleDone,
            onAdd = vm::openNewTask,
        )

        is Screen.Detail -> {
            // Look the task up in the collected list so the screen recomposes when it changes.
            val task = tasks.firstOrNull { it.id == current.taskId }
            if (task == null) {
                // The task was deleted elsewhere; leave the screen outside of composition.
                LaunchedEffect(current) { vm.back() }
            } else {
                TaskDetailScreen(
                    task = task,
                    onBack = { vm.back() },
                    onEdit = { vm.openEdit(task.id) },
                    onToggleDone = { vm.toggleDone(task.id) },
                    onDelete = { vm.delete(task.id) },
                )
            }
        }

        is Screen.Form -> TaskFormScreen(
            initial = current.taskId?.let(vm::task),
            errors = formErrors,
            onSave = { title, notes, due -> vm.saveForm(title, notes, due) },
            onCancel = { vm.back() },
        )
    }
}
