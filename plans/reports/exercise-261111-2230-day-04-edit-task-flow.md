# Day 4 exercise: how editing a task works

Phase: [phase-01-day-04-install-daily-loop.md](../261031-0358-dojo-w2-dev-core/phase-01-day-04-install-daily-loop.md)

## Explanation

Editing a task reuses the add form. The screen state holds the task id, and saving decides between insert and update from that id.

1. `TasksViewModel.openEdit(id)` sets the screen to `Screen.Form(taskId = id)`.
2. `saveForm(title, notes, dueDateText)` validates the title and due date. On error it returns false and keeps the form open.
3. It looks up the existing task from the form's `taskId`, then builds a `Task` with that id, the trimmed title and notes, the parsed due date, and the existing `done` flag.
4. `TaskRepository.save` replaces the task with the same id. A `Task.NEW_ID` (0) inserts instead.
5. The screen moves to `Screen.Detail(saved.id)`.

## Verified claims

1. **The edit keeps the same row.** Claim: saving an edit replaces the task with the same id and does not add a row. Check: `saveForm` passes `id = editing?.id ?: Task.NEW_ID` (`TasksViewModel.kt`), and `InMemoryTaskRepository.save` maps over the list when the id is not `NEW_ID`. Test `editing a task updates it in place instead of copying it` asserts one row after the save. It failed before the fix (issue #4) and passes after.
2. **Editing keeps the done flag.** Claim: `done` comes from the stored task, not from the form. Check: `saveForm` sets `done = editing?.done ?: false`, and the form has no done field. This is by code reading only. No test covers it yet.

## Unresolved

- Claim 2 has no test. Add one if this area changes.
