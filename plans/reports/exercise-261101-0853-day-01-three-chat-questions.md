# Day 1 exercise: three questions in Claude chat

Phase: [phase-02-day-01-mental-model-claude-basics.md](../261031-0357-dojo-w1-shared-fundamentals/phase-02-day-01-mental-model-claude-basics.md)

**How to use this sheet**
- Run each question in its own new chat on claude.ai or in the Desktop app, so each topic gets one thread.
- Send the thin prompt (v1) first. Then send the improved prompt (v2) in a fresh chat, so the two answers can be compared fairly.
- Fill in the blanks below each question.
- Never paste secrets, tokens or customer data. The issue text below is training data, so it is safe to paste.

The v2 prompts use the AI Fluency **Description** checklist:
- **Product:** what output you want, in what format.
- **Process:** how Claude should approach the task.
- **Performance:** what tone or role Claude should take.

---

## 1. Bug summary: issue #3, "Overdue label looks wrong"

**v1 (thin)**

```text
Summarize this bug: "I was looking at my list this morning and a task showed up in red as Overdue, but I'm pretty sure I'm not late on it yet. Kind of stressful! Using the app on my Pixel."
```

**v2 (described)**

```text
I'm a developer on "Dojo Tasks", a small Android to-do app (Kotlin, Jetpack Compose). Tasks have a title and an optional due date; overdue tasks are shown in red.

Turn this user report into a bug summary for a teammate:
- One-line title
- What the user saw vs. what they expected
- What information is missing (device date, task due date, timezone, ...)
- 3 hypotheses worth checking, ranked by likelihood
Keep it under 150 words. Don't invent facts that aren't in the report; mark assumptions as assumptions.

Report: "I was looking at my list this morning and a task showed up in red as Overdue, but I'm pretty sure I'm not late on it yet. Kind of stressful! Using the app on my Pixel."
```

<details>
<summary>v1 answer (verbatim)</summary>

```text
Bug summary: A task shows as "Overdue" (in red) before its due date.

Platform: Android (Google Pixel)
When seen: Morning, on the task list view
Expected: The task shows as upcoming or due, not overdue, until its due date/time has passed.
Actual: The task is flagged red as Overdue too early.
Impact: It worries users ("kind of stressful"), and it makes the Overdue status less trustworthy.
Severity: Medium. It's a display/logic error with no data loss, but it hurts user trust.
Missing info: the task's actual due date/time, the device's timezone, app version, Android version, and whether the task was all-day or had a set time.

Likely causes to check: a timezone or UTC conversion problem, where the due date is stored in UTC but compared against local time (a morning report fits this). Another possibility is an all-day task being treated as due at 00:00 instead of end of day, or an off-by-one in a < vs <= date comparison.
```

</details>

<details>
<summary>v2 answer (verbatim)</summary>

```text
Title: Task shown as Overdue (red) before its due date has passed, Pixel

Observed vs. expected

Observed: In the morning, a task appeared in red with an "Overdue" label.
Expected: The task shouldn't be overdue. The user believes the due date hasn't passed, but isn't certain ("pretty sure").

Missing info

The task's due date, and whether it's date-only or has a time
Device date, time and timezone when this happened
Pixel model, Android version and app version
Any recent travel or timezone change
Screenshot

Hypotheses (most to least likely)

Assumption: A date-only due date is treated as midnight at the start of that day. A task due today would then turn red in the morning instead of after the day ends.
A UTC/local timezone conversion moves the due date one day earlier.
The device clock or timezone is wrong, or the due date isn't what the user remembers.
```

</details>

| | Notes |
|---|---|
| v1 answer, in one line | Generic bug-ticket template, with severity, impact and a list of causes; it states "before its due date" as a fact. |
| v2 answer, in one line | Tighter teammate summary; keeps the user's uncertainty ("pretty sure") and gives ranked hypotheses with the assumption labelled. |
| Which prompt change helped most | "Don't invent facts; mark assumptions as assumptions." It stopped the made-up severity rating and kept the reporter's "pretty sure" uncertainty. The app context (date-only due dates, red = overdue) came second: it made the top hypothesis specific to this app. |
| Discernment: did it invent anything? | v1 added a severity rating ("Medium"), ticket fields and a cause ranking that the report doesn't support, and it took "not late yet" as confirmed. v2 kept to the report and labelled its assumption, though "Pixel" in the title is the only device detail given. |
| Surprise (good or bad) | Good: v2's top hypothesis (a date-only due date compared as start of day) is concrete and testable on Day 2. Bad: v1 sounded just as confident as v2 while adding facts nobody gave it, so a confident tone is no sign of accuracy. |

---

## 2. Concept explanation: state in Jetpack Compose

**v1 (thin)**

```text
Explain state hoisting in Compose.
```

**v2 (described)**

```text
Explain state hoisting in Jetpack Compose to a developer who knows Android Views but is new to Compose.
- Start with a 2-sentence plain-English definition.
- Show one small before/after example: a text field that owns its state vs. one that receives value + onValueChange.
- Explain where a ViewModel with StateFlow fits in.
- End with 3 common mistakes.
If something depends on the Compose version, say so.
```

When you get the answer, check one claim against the [Compose state docs](https://developer.android.com/develop/ui/compose/state). You can also compare it with `android-app/app/src/main/java/com/dojo/tasks/ui/TaskFormScreen.kt`.

<details>
<summary>v1 answer (verbatim)</summary>

````text
State hoisting in Compose means moving state out of a composable and into its caller. The composable becomes **stateless**: it receives the current value as a parameter and reports changes through a callback, usually `value: T` plus `onValueChange: (T) -> Unit`.

**Before (stateful):**

```kotlin
@Composable
fun TaskTitleField() {
    var title by remember { mutableStateOf("") }
    TextField(value = title, onValueChange = { title = it })
}
```

The parent can't read `title`, reset it, or validate it.

**After (hoisted):**

```kotlin
@Composable
fun TaskTitleField(title: String, onTitleChange: (String) -> Unit) {
    TextField(value = title, onValueChange = onTitleChange)
}

@Composable
fun AddTaskScreen() {
    var title by remember { mutableStateOf("") }
    TaskTitleField(title = title, onTitleChange = { title = it })
    Button(onClick = { /* save */ }, enabled = title.isNotBlank()) { Text("Add") }
}
```

This gives you unidirectional data flow: state goes down, events go up.

**Why it's worth doing:**
- **One source of truth.** The state lives in one place, so siblings like the Add button can read the same value.
- **Reusability.** A stateless composable works anywhere, with any state holder.
- **Testability and previews.** You can render it with any value and no setup.
- **Interception.** The parent can validate, transform, or reject changes before applying them.

**Where to hoist it:** to the lowest common ancestor of every composable that reads or writes the state. Don't go higher than that. UI-only state, like whether a dropdown is expanded, can stay local. Screen and business state usually ends up in a `ViewModel` that exposes a `StateFlow`, collected with `collectAsStateWithLifecycle()`.

A common pattern is to offer both versions: a stateful wrapper for convenience that delegates to a stateless overload. That's how many Material components are built.
````

</details>

<details>
<summary>v2 answer (verbatim)</summary>

````text
## The idea in plain English

State hoisting means moving a composable's state *out* of it and up to its caller, so the composable only receives the current value and a callback to request changes. The composable becomes "stateless" (it displays what it's given and reports events), while a single owner higher up decides what the state actually is.

If you're coming from Views: an `EditText` owns its text internally, and you read it with `getText()` or listen with a `TextWatcher`. In Compose, the hoisted version is closer to a fully controlled widget. It has no text of its own. It draws whatever string you pass in and tells you when the user tried to change it.

## Before and after

**Before: the composable owns its state**

```kotlin
@Composable
fun TaskTitleField() {
    var title by remember { mutableStateOf("") }
    OutlinedTextField(
        value = title,
        onValueChange = { title = it },
        label = { Text("Task title") }
    )
}
```

This works, but nobody outside can read `title`, validate it, reset it, or save it. It's also hard to preview or test with a specific value.

**After: state hoisted to the caller**

```kotlin
@Composable
fun TaskTitleField(
    title: String,
    onTitleChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        label = { Text("Task title") },
        modifier = modifier
    )
}

@Composable
fun AddTaskScreen() {
    var title by rememberSaveable { mutableStateOf("") }
    Column {
        TaskTitleField(title = title, onTitleChange = { title = it })
        Button(onClick = { /* save title */ }, enabled = title.isNotBlank()) {
            Text("Add")
        }
    }
}
```

Now the screen owns `title`, so it can enable the button, clear the field after saving, and so on. `TaskTitleField` is reusable and trivially previewable. The pattern is often summarized as "state flows down, events flow up."

## Where a ViewModel with StateFlow fits

Hoisting is a ladder, and the rule is to hoist state to the *lowest common owner that needs it*. Purely visual state (is a dropdown expanded?) can stay in the composable. State that the screen needs goes to the screen composable. State that involves business logic, survives configuration changes, or comes from a repository or database goes up to a ViewModel.

At the ViewModel level, the screen-level composable collects the flow and passes plain values down:

```kotlin
data class TasksUiState(val tasks: List<Task> = emptyList(), val isLoading: Boolean = false)

class TasksViewModel(private val repo: TaskRepository) : ViewModel() {
    val uiState: StateFlow<TasksUiState> = repo.observeTasks()
        .map { TasksUiState(tasks = it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TasksUiState(isLoading = true))

    fun onTaskChecked(id: Long, done: Boolean) {
        viewModelScope.launch { repo.setDone(id, done) }
    }
}

@Composable
fun TasksRoute(viewModel: TasksViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    TasksScreen(state = state, onTaskChecked = viewModel::onTaskChecked)
}
```

The usual split is a thin "route" composable that knows about the ViewModel, and a `TasksScreen` beneath it that takes only plain values and lambdas. That keeps everything below the route previewable and testable without a ViewModel.

Version notes:

- `collectAsStateWithLifecycle()` comes from `androidx.lifecycle:lifecycle-runtime-compose` (2.6.0+). It stops collecting when the lifecycle drops below STARTED. Plain `collectAsState()` works everywhere but keeps collecting in the background.
- Text input is changing. Compose Foundation 1.7 introduced the state-based `BasicTextField(state = TextFieldState)` API, and Material 3 `TextField` overloads using it arrived later. With that API you hoist a `TextFieldState` object (e.g. `rememberTextFieldState()`, or one held in the ViewModel) instead of a `value`/`onValueChange` pair. The value/callback version shown above is still supported and is what most existing code and tutorials use.

## Three common mistakes

1. **Backing a text field with a StateFlow that updates asynchronously.** If `onValueChange` goes through a coroutine, a `debounce`, or a `combine` before the new text comes back, the field can momentarily render stale text. Users then see the cursor jump or lose typed characters. Text field state must update synchronously. Either keep it in a `mutableStateOf` (in the composable or the ViewModel), or use `TextFieldState` on newer Compose versions. Derive other flows from it, rather than round-tripping keystrokes through a flow.

2. **Hoisting too high, or not high enough.** Pushing every bit of UI state into the ViewModel bloats it and couples it to visual details, like whether a menu is open. Leaving shared state buried in a child forces awkward workarounds when a sibling needs it. Ask yourself who actually reads or changes this value, and put it at their lowest common ancestor.

3. **Passing the ViewModel (or a MutableStateFlow) down the tree.** Handing `viewModel` to deep children makes them unpreviewable and lets any child mutate anything. Pass immutable values and specific lambdas like `onTaskChecked` instead. A related slip is using `remember` where `rememberSaveable` is needed. Plain `remember` loses its value on rotation and process death, which `EditText` users don't expect, because Views saved their text automatically.
````

</details>

| | Notes |
|---|---|
| v1 answer, in one line | Correct, compact definition with a before/after example and the "lowest common ancestor" rule; uses `remember`, no version notes. |
| v2 answer, in one line | Same core idea, framed for a Views developer (`EditText` comparison), plus a route/screen ViewModel pattern, version notes and three mistakes; switches to `rememberSaveable`. |
| Which prompt change helped most | Naming the audience ("knows Android Views, new to Compose"). It produced the `EditText` comparison, which made the idea click. "If something depends on the Compose version, say so" came second: it surfaced the `TextFieldState` and `collectAsStateWithLifecycle` notes that v1 left out. |
| Claim verified against the docs, and the result | Checked both version notes against the AndroidX release notes. **`collectAsStateWithLifecycle()` in `lifecycle-runtime-compose` 2.6.0+: holds.** It was added in 2.6.0-alpha01 and is stable from 2.6.0. **"Foundation 1.7 introduced `TextFieldState`": mostly holds.** The API was already in Foundation 1.6 as the experimental `BasicTextField2`; `BasicTextField(state)` and `TextFieldState` became stable in 1.7.0-alpha05. |
| Compared with the Dojo Tasks code | The app matches the v2 pattern. `TaskFormScreen` keeps its fields in `rememberSaveable { mutableStateOf(...) }` at screen level, `TasksViewModel` exposes `StateFlow`s, and `MainActivity` acts as the route that calls `collectAsStateWithLifecycle()`. |
| Surprise (good or bad) | Good: v2's mistake #1 (async text-field updates make the cursor jump) is a real pitfall I wouldn't have thought to ask about. Bad: the version note was close but simplified; `TextFieldState` predates 1.7 under another name, so version claims still need a source check. |

---

## 3. Drafting task: status update to your lead

**v1 (thin)**

```text
Write a status update about my training progress.
```

**v2 (described)**

```text
Draft a short status update to my team lead about my Claude Code training (15-day program, I'm doing it solo on the Android track).

Facts to include, and nothing else:
- Day 0 done: built the "Dojo Tasks" training app (Kotlin/Compose) with unit tests and GitHub Actions CI; seeded 7 backlog issues.
- Day 1 in progress: finished the AI Fluency course summary; Claude 101 and a chat exercise remain.
- Next: Day 2, ticket analysis and reproducing a bug.

Format: 3 bullets (Done / Next / Blockers), plain and direct, no hype, under 80 words. Blockers: none.
```

<details>
<summary>v1 answer (verbatim)</summary>

```text
Happy to write it. What training is this, and what have you done so far? For example, a Compose course you're working through, a certification, or something else, plus who the update is for.

In the meantime, here's a skeleton I can fill in once I have the details:

**Training status update – [name of training]**

**Progress:** Completed [X of Y modules / weeks], covering [main topics]. On track for [target date], or [behind/ahead by N weeks].

**Applied so far:** Used [skill or concept] in [project or task], which [result].

**Next up:** [Upcoming modules or topics] over the next [timeframe].

**Blockers / needs:** [Anything you need, such as time, access, or a reviewer], or "None at the moment."
```

</details>

<details>
<summary>v2 answer (verbatim)</summary>

```text
**Claude Code training (15-day program, solo, Android track)**

- **Done:** Day 0 is complete. I built the "Dojo Tasks" training app (Kotlin/Compose) with unit tests and GitHub Actions CI, and seeded 7 backlog issues. Day 1 is in progress: the AI Fluency course summary is finished, and Claude 101 and a chat exercise remain.
- **Next:** Day 2, ticket analysis and reproducing a bug.
- **Blockers:** None.
```

</details>

| | Notes |
|---|---|
| v1 answer, in one line | No usable draft. Claude asked what the training is and who the update is for, and gave a fill-in-the-blanks template instead of making up progress. |
| v2 answer, in one line | Send-ready update: Done / Next / Blockers, 64 words, only the given facts. It added a heading line built from the prompt's context. |
| Which prompt change helped most | Giving the facts myself, with "and nothing else". v1 had no facts, so it could only ask questions; v2 had nothing to make up. The fixed format (3 bullets, under 80 words) made it ready to send as-is. |
| Edits you made before you would send it | Minor: drop the heading line (my lead knows the program) and update the Day 1 line, since the chat exercise is now done and only Claude 101 and the AI Fluency quiz remain. |
| Surprise (good or bad) | Good: with a thin prompt, v1 asked clarifying questions instead of inventing progress. That's the opposite of Question 1, where the thin prompt got made-up details. Claude asks when the gap is obvious, but not always. |

---

## Reflection

- **Where did the output surprise you, for better or worse?** Thin prompts behaved inconsistently. For the bug report Claude filled the gaps with confident made-up details (a severity rating, causes stated as likely). For the status update it asked questions instead. Even the good v2 answers had small slips: the `TextFieldState` version note was simplified. Checking against a source is still my job.
- **Which Description element (product, process or performance) helped most overall?** Product: saying exactly what output I wanted (sections, length, format) made the biggest difference in all three. Process came next in Question 1, where "don't invent facts; mark assumptions" changed the content, not just the shape. Performance, meaning the audience, mattered most in Question 2.
- **One prompting habit to keep for Day 2:** Give Claude the facts and context, state the output format, and add "don't invent facts; mark assumptions." Day 2's ticket analysis works from vague reports, so that rule should stop it from inventing repro details.
