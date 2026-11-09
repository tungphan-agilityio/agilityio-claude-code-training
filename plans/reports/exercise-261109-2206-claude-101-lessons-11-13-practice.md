# Claude 101 lessons 11–13 and quiz: hands-on practice

Phase: [phase-03-day-02-ticket-analysis-reproduce.md](../261031-0357-dojo-w1-shared-fundamentals/phase-03-day-02-ticket-analysis-reproduce.md) · Course notes: [course-summary-261101-2107-claude-101.md](course-summary-261101-2107-claude-101.md#module-4-putting-it-all-together) · Previous: [lesson 10 practice](exercise-261108-1412-claude-101-lesson-10-research-practice.md)

**How to use this sheet**
- Take each lesson on the Academy first, then do its part here. Parts A and B use claude.ai or the Claude Desktop **Chat** tab. Start each in a **new chat**.
- Lessons 11–13 are reading lessons. The practice below is what makes them stick.
- Lesson 13's advice is "pick one recurring task". Part C does that for Day 3.

**The goal.** Use the use-case pattern on real repo data, match each step of the work so far to the right Claude tool, and finish the course with a quiz you can trust.

---

## Part A: lesson 11, patterns in user feedback

Lesson point: every gallery use case is the same pattern: **bring real context, ask for a specific output, check it.**

Open the [Use Case Gallery](https://claude.com/resources/use-cases) and read the "patterns in user feedback" entry. Then, in a **new chat**, paste the 7 backlog issue titles and bodies from the [issues page](https://github.com/tungphan-agilityio/agilityio-claude-code-training/issues) (sandbox data only), followed by:

```text
These are 7 bug and feature reports for a small Android to-do app. Group them into clusters by likely root cause. For each cluster give: the issue numbers, the shared cause you suspect, and what in the report makes you think so. Mark anything you're guessing. Don't invent issues that aren't listed.
```

| Check | Notes |
|---|---|
| Did #3 and #9 cluster together? (My Day 2 analysis linked them.) | |
| Did #4 and #5 cluster together? (The Day 2 partner linked them.) | |
| Did it mark guesses, or state them as fact? | |
| Did it invent anything not in the 7 issues? | |
| Which cluster would change what you do first? | |

Known-answer rule: I already know #3's cause (the `!isAfter` boundary), so check whether Claude's cluster for #3 names that cause or just a vague "date handling".

---

## Part B: lesson 12, match the tool to the work

Lesson point: Claude Code is for building, debugging and chores. Claude in Chrome is for low-risk browser flows. Claude.ai chat is for analysis and writing. Cowork is for multi-step documents and files.

Fill in the tool for each Day 1–2 step *before* opening the suggested answers.

| # | Step I did | Tool I'd pick | Why |
|---|---|---|---|
| 1 | Analyse 5 backlog tickets in 3 sentences each | | |
| 2 | Reporter role-play for #3 | | |
| 3 | Reproduce #3 on the emulator and write a failing test | | |
| 4 | Resolve seeded merge conflict #1 | | |
| 5 | Read #6 and #8 from GitHub in a browser | | |
| 6 | Compare Room vs DataStore for #7 | | |
| 7 | Turn a recurring bug-report format into a skill | | |

<details>
<summary>Suggested answers</summary>

1. **Claude.ai chat.** It's analysis and writing, with no code to change.
2. **Claude.ai chat**, in a fresh chat with no repo access, so it can't leak technical guesses.
3. **Claude Code.** It runs the app and tests and edits files.
4. **Claude Code.** Merge conflicts are a named chore.
5. **Claude in Chrome**, or web fetch. It's a low-risk read on a trusted site.
6. **Claude.ai Research.** It's a multi-source report with citations.
7. **Claude.ai** to build and test it first, then Claude Code for a team version in `.claude/` on Day 7.

</details>

| | Notes |
|---|---|
| Matches out of 7 | |
| One step where two tools would both work, and what decides it | |

---

## Part C: lesson 13, pick one recurring task

Lesson point: start simple, pick one recurring task, iterate.

The recurring task is the **PR description**. Day 3 needs two of them. In **Claude Code**, after the fix exists, ask for a draft covering what, why, how tested and screenshots. Then hand-edit it.

| | Notes |
|---|---|
| What did you change by hand, and why? | |
| Could a reader review the PR from the description alone? | |
| One instruction to add to the next prompt | |

(Do this on Day 3. Leave the table blank until then.)

---

## Part D: course quiz and badge

Take the quiz on the Academy. Don't ask Claude for the answers. The score only means something if you take it cold.

| | Notes |
|---|---|
| Score | |
| Questions I got wrong, and which lesson they came from | |
| Badge earned? | |

---

## Wrap-up

- Lessons 11, 12 and 13 done (tick them in the Day 2 phase file, or tell Claude Code): ☐
- Fill the lesson 11–13 and quiz rows in section 5c of the [Day 2 sheet](exercise-261105-2255-day-02-ticket-analysis.md), and rewrite any takeaway that doesn't match what you saw.
- Lessons 9 and 10 also still need ticking. Lesson 9 is skipped as Team and Enterprise only, and lesson 10 waits on its practice sheet.
