package com.dojo.tasks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.dojo.tasks.R
import com.dojo.tasks.data.Task
import com.dojo.tasks.domain.TaskRules
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onOpen: (Long) -> Unit,
    onToggleDone: (Long) -> Unit,
    onAdd: () -> Unit,
) {
    val sorted = remember(tasks) { TaskRules.sortForList(tasks) }
    val today = LocalDate.now()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(stringResource(R.string.tasks_title))
                        Text(
                            stringResource(R.string.remaining_count, TaskRules.remainingCount(tasks)),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Text("+", style = MaterialTheme.typography.headlineSmall) }
        },
    ) { padding ->
        if (sorted.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.empty_tasks))
            }
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(padding)) {
                items(sorted, key = { it.id }) { task ->
                    TaskRow(task, today, onOpen = { onOpen(task.id) }, onToggleDone = { onToggleDone(task.id) })
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun TaskRow(task: Task, today: LocalDate, onOpen: () -> Unit, onToggleDone: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable(onClick = onOpen),
        leadingContent = { Checkbox(checked = task.done, onCheckedChange = { onToggleDone() }) },
        headlineContent = {
            Text(
                task.title,
                textDecoration = if (task.done) TextDecoration.LineThrough else null,
            )
        },
        supportingContent = { DueLabel(task, today) },
    )
}

@Composable
fun DueLabel(task: Task, today: LocalDate, modifier: Modifier = Modifier) {
    val due = task.dueDate
    when {
        TaskRules.isOverdue(task, today) -> Text(
            "${stringResource(R.string.overdue)} · ${stringResource(R.string.due_on, due.toString())}",
            color = MaterialTheme.colorScheme.error,
            modifier = modifier,
        )

        due != null -> Text(stringResource(R.string.due_on, due.toString()), modifier = modifier)
        else -> Text(
            stringResource(R.string.no_due_date),
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.padding(top = 2.dp),
        )
    }
}
