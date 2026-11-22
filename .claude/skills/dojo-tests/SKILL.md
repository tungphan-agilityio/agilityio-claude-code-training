---
name: dojo-tests
description: How unit tests are written in the Dojo Tasks Android app (JUnit 4, fakes, backtick names, fixed dates). Use when writing or reviewing tests under android-app/app/src/test.
---
# Dojo Tasks test conventions

## Where tests go
- Business rules: `domain/TaskRules.kt` → `src/test/java/com/dojo/tasks/domain/TaskRulesTest.kt`. Most new tests belong here.
- Repository: `InMemoryTaskRepositoryTest`.
- ViewModel flows (save, edit, navigation, search): `ui/TasksViewModelTest`.
- No Compose UI or instrumented tests yet; do not add androidTest or new test dependencies without a ticket.

## Style
- JUnit 4 (`org.junit.Test`, `org.junit.Assert.*`). No MockK, Turbine or coroutine test libraries: they are not dependencies.
- Name tests with backticks as a behaviour sentence: `` `task due today is not overdue` ``.
- Use a fixed date, never `LocalDate.now()`: `private val today = LocalDate.of(2026, 11, 2)` and pass `today` into the rule.
- Use the real `InMemoryTaskRepository` as the fake; build it with the tasks the test needs.
- Read ViewModel state through `.value` on its `StateFlow`s (`vm.screen.value`, `vm.formErrors.value`).
- One behaviour per test. Assert the outcome the user sees, not internal calls.
- For a bug fix, add a comment with the issue number above the test, e.g. `// Issue #3: ...`, and make sure it fails before the fix.

## Example

```kotlin
// Issue #3: a task due today shows as Overdue, but the user is not late yet.
@Test
fun `task due today is not overdue`() {
    assertFalse(TaskRules.isOverdue(Task(title = "t", dueDate = today), today))
}
```

## Running
From `android-app/`:
- One class: `./gradlew testDebugUnitTest --tests "com.dojo.tasks.domain.TaskRulesTest"`
- All: `./gradlew testDebugUnitTest`
