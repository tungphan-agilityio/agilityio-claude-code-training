# Claude 101 lesson 10: Research mode for deep dives, hands-on practice

Phase: [phase-03-day-02-ticket-analysis-reproduce.md](../261031-0357-dojo-w1-shared-fundamentals/phase-03-day-02-ticket-analysis-reproduce.md) · Course notes: [course-summary-261101-2107-claude-101.md](course-summary-261101-2107-claude-101.md#lesson-10-research-mode-for-deep-dives-day-2-paid-plans) · Previous: [lesson 8 practice](exercise-261108-0916-claude-101-lesson-08-connectors-practice.md)

Lesson 9 (Enterprise Search) is skipped because it's Team and Enterprise only, and this is a solo plan. Its row in the [Day 2 sheet](exercise-261105-2255-day-02-ticket-analysis.md) covers what I'd ask it at work.

**How to use this sheet**
- Do each part on claude.ai or in the Claude Desktop app **Chat** tab, not Claude Code. Start every part in a **new chat**.
- Research needs a paid plan. Turn it on with **+ > Research**, with web search on. If it isn't offered, write "not on my plan" and do Parts A and D only.
- Research takes **minutes**. Start it, then do something else.
- The research question is real: issue **#7, "App forgets my tasks"**. Day 2 classed it as a feature (persistence isn't built yet), and this research is the groundwork for scoping it.

**The goal.** Pick the right tool for each question (Research, web search, Thinking, Enterprise Search), write a Research prompt that's worth the minutes it takes, and check its citations instead of trusting the polish.

**Known facts the research must fit** (from `android-app/` on 2026-11-08)

| Fact | Value |
|---|---|
| Language / UI | Kotlin 2.4.21, Jetpack Compose, Material 3 |
| Build | AGP 9.4.1, compileSdk 37, targetSdk 36, **minSdk 26** |
| Storage today | In memory only (`InMemoryTaskRepository`). No Room or DataStore dependency yet. |
| Data | `Task(id, title, notes, dueDate: LocalDate?, done)`. A handful to a few hundred tasks, one user, on the device only. |
| Out of scope for #7 | Cloud sync, accounts, multi-device |

---

## Part A: send each question to the right tool

Lesson point: **Research** is for reports drawn from many sources, with citations. **Web search** is for one quick fact. **Thinking** is for reasoning without outside information. **Enterprise Search** is for your organization's own knowledge.

Fill in your pick *before* opening the answers.

| # | Question | Your pick | Why |
|---|---|---|---|
| 1 | "Compare Room, DataStore and SQLDelight for storing a few hundred to-do tasks in a Compose app: trade-offs, migration story, testing." | Research | Compares several options across many sources, and the citations matter. |
| 2 | "What's the latest stable version of Room?" | Web search | One fact, and speed matters. Check it against the official release notes. |
| 3 | "Why does `!due.isAfter(today)` mark a task due today as overdue?" | Thinking | It's logic about one line of code, so outside sources add nothing. |
| 4 | "Has anyone on our team already built offline storage for an Android app?" | Enterprise Search | It's internal knowledge, and it isn't on a solo plan. Fallback: ask a teammate. |
| 5 | "Does DataStore support `minSdk 26`?" | Web search | A single compatibility fact. |
| 6 | "Survey how popular to-do apps (open source) handle overdue logic and time zones, with examples." | Research | A survey of many sources with examples. |
| 7 | "Given these 7 issue titles, which two are probably related?" | Thinking | All the context is in the prompt. |

<details>
<summary>Suggested answers</summary>

1. **Research.** It compares several options across docs, release notes and articles, and the citations matter.
2. **Web search.** One fact, and speed matters. Check it against the official release notes.
3. **Thinking.** It's logic about one line of code, so outside sources add nothing. (Day 2 proved it with a test.)
4. **Enterprise Search.** It's internal knowledge, and it isn't on a solo plan. The fallback is to ask a teammate.
5. **Web search.** It's a single compatibility fact.
6. **Research.** It's a survey of many sources with examples.
7. **Thinking.** The context is all in the prompt. (The Day 2 partner linked #4 and #5, and I linked #3 and #9.)

</details>

| | Notes |
|---|---|
| Matches out of 7 | |
| Which one did you get wrong, and why was it tempting? | |

---

## Part B: let Claude help write the Research prompt

Lesson tip: give a specific goal, the sections you want, and your constraints, and have Claude help draft the Research prompt first.

In a **new chat**, with **Research off**, paste:

```text
Help me write a prompt for Claude's Research mode. Don't do the research yet.

Goal: choose how to add local persistence to a small Android to-do app (issue: "App forgets my tasks").
Facts: Kotlin 2.4.21, Jetpack Compose, AGP 9.4.1, compileSdk 37, minSdk 26. Data model: Task(id, title, notes, dueDate: LocalDate?, done). A few hundred tasks max, single user, on-device only. No cloud sync, accounts or multi-device.
I want a short decision report.

Interview me briefly if anything important is missing, then give me the final Research prompt with: the goal, the sections the report must have, the constraints, and what sources to prefer.
```

Answer its questions. Then check the prompt it gives you:

| Check | Pass? | Notes |
|---|---|---|
| It has all the known facts (versions, minSdk 26, data model, out of scope) | Pass | Kotlin, AGP, compileSdk 37, minSdk 26, data model, scale, and out-of-scope items all present. |
| It asks for set **sections** (e.g. options, trade-offs, migration, testing, recommendation) | Pass | Six sections, including recommendation, options table, implementation notes, risks, and assumptions. |
| It asks for **official sources first** (developer.android.com, release notes) | Pass | developer.android.com is listed first in the source order. |
| It asks for a **date or version on every claim about the "latest" anything** | Fail | It says "verify current stable versions" and "as of November 2026" only for maintenance. It doesn't require a date or version on each claim. |
| It keeps cloud sync out of scope | Pass | Stated as out of scope in the context block. |
| Anything it added that you didn't ask for (good or bad)? | Added | Named candidates (JSON file, SQLDelight, DataStore variants), a Flow/Compose assumption, Robolectric testing, an 800-word cap, and assumptions about encryption, sub-second writes, and no background sync. The last three aren't in the known facts, so confirm them before Part C. |

---

## Part C: run Research, then check its citations

In a **new chat**, turn on **+ > Research**, paste the final prompt from Part B, and let it run.

| | Notes |
|---|---|
| How long did it take? How many sources did it cite? | 5m 12s, 278 sources (per the Research completion card) |
| Recommendation (one line) | Room 3.0.3 with Flow queries, over DataStore, SQLDelight and a JSON file. |
| Did it stay in scope (no cloud sync, no accounts)? | Yes. It doesn't add sync or accounts. It does include Gradle dependency lines, which the prompt said to leave out (outline only). |
| Did it use the facts (minSdk 26, `LocalDate` due dates, a few hundred rows)? | Yes. It covers minSdk 26, a `LocalDate` to `Long` converter, and the few-hundred-row scale. It is over the 800-word cap. |

**Spot-check 3 citations.** Pick the 3 claims the recommendation leans on most. Open each source and check that it says what the report claims.

| Claim | Source | Does the source say that? | Current? (date or version shown) |
|---|---|---|---|
| Room 3.0.3 is stable and was released Sep 9, 2026 | developer.android.com Room 3.0 release page and recent release notes | Yes. Both list 3.0.3 on Sep 9, 2026. A later "Oct 21, 2026" date on the summary table looks like a page update, not a release. | Yes, Sep 9, 2026 |
| DataStore is for small datasets, not partial updates or referential integrity | developer.android.com DataStore architecture guide | Yes in substance. Search snippets match the wording, but I didn't open the page, so the exact quote is unconfirmed. | Undated page. The API reference confirms the partial-update limit. |
| KGP 2.4.20–2.4.21 is supported with AGP "8.5.2–9.3.1" only | kotlinlang.org "Configure a Gradle project" | Yes. The page gives that range. It says newer AGP may work, with warnings or missing features. | Undated page, but the Kotlin versions it lists are 2.4.20–2.4.21 |

**One known-answer check.** Ask in the same chat:

```text
Which version of Room (or DataStore, whichever you recommended) should I add, and does it support Kotlin 2.4.21 and AGP 9.4.1? Cite the release notes.
```

Then check the answer against the official release notes yourself. "Latest version" claims go stale fastest, and this is where a polished report is most likely to be wrong.

| | Notes |
|---|---|
| Version it named, and where from | Room 3.0.3 (`androidx.room3`) and KSP 2.3.13, as the report cites them (AndroidX release notes; the report gives no source for KSP 2.3.13). Room 3.0.3 matches the release notes. KSP 2.3.13 is not in the release notes I could open. |
| Matches the official release notes today? | Partly. Room 3.0.3 matches. Kotlin 2.4.21 with AGP 9.4.1 is outside the documented range (AGP up to 9.3.1). A search also reports that KSP 2.3.13 has a concurrency regression. I couldn't confirm that from the release notes, so the report's KSP recommendation is doubtful. |

---

## Part D: when *not* to use Research

In a **new chat**, with **Research off** and Thinking on if offered, paste:

```text
This Kotlin function marks a task due today as overdue. Explain why in two sentences, then give the corrected condition.

fun isOverdue(task: Task, today: LocalDate): Boolean {
    val due = task.dueDate ?: return false
    return !task.done && !due.isAfter(today)
}
```

| | Notes |
|---|---|
| Answer correct? (Expected: `!isAfter` means "on or before"; the fix is `due.isBefore(today)`.) | |
| Time taken vs Part C | |
| Would Research have added anything here? | |

This function is the Day 3 fix. If Claude's answer matches, you already know what Day 3's change is. The test on `day-2-issue-3-overdue-repro` still decides whether it's right.

---

## Wrap-up

- Lesson 10 done (tick it in the Day 2 phase file, or tell Claude Code): ☑
- Does the lesson 10 takeaway in the [Day 2 sheet](exercise-261105-2255-day-02-ticket-analysis.md) still match? Rewrite it if not: **No, rewritten.** The takeaway missed the main lesson. Research is a draft, and its citations can be real while its version claims are still unverified, so every "latest" or compatibility claim needs checking against the release notes.
- One citation that didn't hold up, or one that did (and how you checked): **Held up:** the Room 3.0.3 date (Sep 9, 2026), checked on the [Room 3.0 release page](https://developer.android.com/jetpack/androidx/releases/room3) and the [recent release notes](https://developer.android.com/jetpack/androidx/versions/all-channel). **Didn't hold up:** the claim that KSP 2.3.13 works with Kotlin 2.4.21 and AGP 9.4.1. The Kotlin docs list AGP only up to 9.3.1, and the KSP regression couldn't be confirmed from the release notes. Still open: check the [google/ksp releases page](https://github.com/google/ksp/releases).
- For #7, would you act on the recommendation as it stands? What would you check first? **No, not yet.** Room is a reasonable direction, but the KSP version and the Kotlin/AGP pairing aren't confirmed. First, check whether KSP 2.3.13 has the regression and which KSP version works with Kotlin 2.4.21 and AGP 9.4.1. Second, compile a build with those versions in a spike branch. Third, check the DataStore quote against the official page word for word. These are the same checks the Day 3 fix needs.
