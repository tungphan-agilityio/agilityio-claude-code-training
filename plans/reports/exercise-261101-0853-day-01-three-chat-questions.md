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
| Which prompt change helped most | |
| Discernment: did it invent anything? | v1 added a severity rating ("Medium"), ticket fields and a cause ranking that the report doesn't support, and it took "not late yet" as confirmed. v2 kept to the report and labelled its assumption, though "Pixel" in the title is the only device detail given. |
| Surprise (good or bad) | |

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

| | Notes |
|---|---|
| v1 answer, in one line | |
| v2 answer, in one line | |
| Which prompt change helped most | |
| Claim verified against the docs, and the result | |
| Surprise (good or bad) | |

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

| | Notes |
|---|---|
| v1 answer, in one line | |
| v2 answer, in one line | |
| Which prompt change helped most | |
| Edits you made before you would send it | |
| Surprise (good or bad) | |

---

## Reflection

- Where did the output surprise you, for better or worse?
- Which Description element (product, process or performance) helped most overall?
- One prompting habit to keep for Day 2:
