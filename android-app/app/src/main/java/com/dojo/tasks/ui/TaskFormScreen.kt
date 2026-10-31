package com.dojo.tasks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dojo.tasks.R
import com.dojo.tasks.data.Task
import com.dojo.tasks.domain.TitleError

/** Add/edit form. [initial] is null when adding a new task. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    initial: Task?,
    errors: FormErrors,
    onSave: (title: String, notes: String, dueDate: String) -> Unit,
    onCancel: () -> Unit,
) {
    var title by rememberSaveable { mutableStateOf(initial?.title.orEmpty()) }
    var notes by rememberSaveable { mutableStateOf(initial?.notes.orEmpty()) }
    var due by rememberSaveable { mutableStateOf(initial?.dueDate?.toString().orEmpty()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(if (initial == null) R.string.new_task else R.string.edit_task)) })
        },
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.title_label)) },
                isError = errors.title != null,
                supportingText = errors.title?.let { { Text(stringResource(it.messageRes())) } },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text(stringResource(R.string.notes_label)) },
                minLines = 3,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = due,
                onValueChange = { due = it },
                label = { Text(stringResource(R.string.due_label)) },
                isError = errors.badDate,
                supportingText = if (errors.badDate) {
                    { Text(stringResource(R.string.error_bad_date)) }
                } else {
                    null
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onSave(title, notes, due) }) { Text(stringResource(R.string.save)) }
                OutlinedButton(onClick = onCancel) { Text(stringResource(R.string.cancel)) }
            }
        }
    }
}

private fun TitleError.messageRes() = when (this) {
    TitleError.REQUIRED -> R.string.error_title_required
    TitleError.TOO_LONG -> R.string.error_title_too_long
}
