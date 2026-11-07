# Claude 101 lesson 7: working with skills, hands-on practice

Phase: [phase-03-day-02-ticket-analysis-reproduce.md](../261031-0357-dojo-w1-shared-fundamentals/phase-03-day-02-ticket-analysis-reproduce.md) · Course notes: [course-summary-261101-2107-claude-101.md](course-summary-261101-2107-claude-101.md#lesson-7-working-with-skills-day-2) · Day 2 log: [exercise-261105-2255-day-02-ticket-analysis.md](exercise-261105-2255-day-02-ticket-analysis.md)

**How to use this sheet**
- Do each part on claude.ai or in the Claude Desktop app, right after reading the lesson.
- Paste the prompts as they are, then fill in the notes tables.
- First turn on **Settings > Capabilities > Code execution and file creation**. Skills are managed under **Customize > Skills**. If an option isn't there, write "not on my plan" and move on.
- Share only public training content: issue text and the repro you posted on #3. Don't upload source files from `android-app/`.

**The goal.** By the end you'll have a custom skill that turns a raw bug report into a reproduce-ready report, using the format from Day 2. You'll also have checked it against a case where you already know the right answer.

---

## Part A: watch a built-in skill fire

Lesson point: Anthropic skills (Excel, Word, PowerPoint, PDF) load by themselves when a task needs them, and the skill's name shows in Claude's reasoning.

In a **new chat**, paste:

```text
Make an Excel file listing these backlog tickets from a to-do app, with columns: Issue, Title, Type (bug/enhancement), Priority guess (P1-P3), One-line reason. Add a header row, freeze it, and turn on a filter.

#3 Overdue label looks wrong (bug)
#4 Editing a task makes a copy (bug)
#5 Blank task in my list (bug)
#6 Search tasks (enhancement)
#7 App forgets my tasks (enhancement)
#8 Show overdue count in the header (enhancement)
#9 Make the list less confusing (enhancement)
```

**Run 1 (2026-11-07): in a Claude Code session in this repo, not claude.ai chat.** The file was written to the repo root as `backlog-tickets.xlsx` and not committed.

| | Notes |
|---|---|
| Which skill did Claude name in its reasoning? | **Not shown.** The reply doesn't name a skill. In Claude Code the file is likely written by a script rather than claude.ai's built-in xlsx skill, so this run doesn't test the lesson's point. **Re-run the prompt in claude.ai chat** and look for the skill name in the reasoning. |
| Did you get a real `.xlsx` file (frozen header, filter on)? | **Yes, checked independently** by unzipping the file. It has 1 header row plus 7 rows (`A1:E8`), a frozen pane below row 1 (`ySplit="1"`, `state="frozen"`) and an auto-filter on `A1:E8`. Claude also said what it had *not* checked ("I haven't opened it in Excel itself"). That's the honest verification habit from lesson 3. |
| Ask for a change ("sort by priority"). Did it edit the file, or give you a *new* version? | Not done yet. Ask it in the claude.ai re-run. |

**Run 2 (2026-11-07): claude.ai chat, new conversation, same prompt.**

| | Notes |
|---|---|
| Which skill did Claude name in its reasoning? | **Check the reasoning panel.** The reply itself doesn't name a skill. But the output path `/mnt/user-data/outputs/backlog_tickets.xlsx` is claude.ai's sandbox, so code execution and file creation ran there. Expand Claude's reasoning or tool steps and look for the xlsx skill being read. |
| Did you get a real `.xlsx` file (frozen header, filter on)? | Claude says it made one sheet, "Backlog", with a bold header row, the header frozen and a filter on all columns. **Open the download in Excel or Numbers to confirm.** Run 1 was checked by unzipping the file, but this one hasn't been checked. |
| Ask for a change ("sort by priority"). Did it edit the file, or give you a *new* version? | Not done yet. |
| Compared with run 1 | It lists its assumptions up front ("I only had the ticket titles"), which is better calibrated. **It made the same #7 miss:** "sounds like a persistence bug, so you may want to re-type it". Two runs without product context gave the same wrong guess, so it's a context gap, not chance. Lesson 3's fix still applies: add one line saying the app keeps data in memory by design. |

**Content check (the lesson 3 habit):**
- **#7 at P1, "probably a bug in practice", is the same miss as lesson 3 run 1.** The README says data lives in memory and resets on restart *by design*, so #7 is a feature request. It may still deserve high priority, but not because it's a bug. Claude kept the Type column as given, then argued against it in its reasoning. Give it the product context and the rating should change.
- **#8 "depends on #3 being correct" is a good catch.** An overdue count in the header would repeat #3's wrong boundary, so fix #3 first.
- **#3 at P2 ("misleading, no data affected") is fair.** The fix is a one-line change (Day 3), so it's cheap whatever the priority.
- The priorities are guesses, and the reply labels them as guesses. A human sets the final priority.

Expected: the xlsx skill is named, and the file opens with a frozen header and a filter. A change request returns a **new** file rather than editing the old one in place, because uploads and outputs are processed in a sandbox.

---

## Part B: build a custom skill through the interview

Lesson point: describe the workflow in a chat, answer Claude's interview questions, upload reference material, then use **Save skill**.

1. In a **new chat**, paste:

```text
I want to create a custom skill called "bug-repro-report". It turns a raw user bug report from a mobile app into a report a developer can reproduce from alone, without asking the reporter anything.

The output must have these sections, in this order:
1. Title: one line, the symptom, not the cause.
2. Steps: numbered, starting from app launch, with exact inputs (e.g. a date in yyyy-mm-dd).
3. Expected vs Actual.
4. Frequency: e.g. 100%, intermittent, once.
5. Environment: device, OS/API level, app version or commit, time zone and date if dates are involved.
6. Failing test (if known): test name and branch.
7. Likely cause, "verified by running" or "unverified": never present a guess as a finding.
8. Questions for the reporter: only the ones still open.

Rules: never invent facts. If a section can't be filled from what I give you, write "Unknown – ask reporter" and add a question to section 8. Keep it under 250 words.

Interview me about anything else you need before writing the skill.
```

2. Answer its questions. Some likely ones, with suggested answers:
   - *Who reads it?* A developer on the team, inside a GitHub issue comment.
   - *Format?* GitHub-flavoured markdown, with bold section labels.
   - *Should it propose a fix?* No. The fix belongs on another day. Name the likely cause only.
3. When it asks for reference material, paste your **#3 repro comment** as the gold example: open the [comment on #3](https://github.com/tungphan-agilityio/agilityio-claude-code-training/issues/3#issuecomment-6662720301) and copy its text.
4. Click **Save skill**. Then open **Customize > Skills** and **read the saved skill before you use it**. This is the lesson's security habit, and the skill is a file you can inspect.

| | Notes |
|---|---|
| Questions Claude asked in the interview (list them) | |
| Anything it asked that you hadn't thought of? | |
| After reading the saved skill: does it contain all 8 sections and the "never invent" rule? Any executable code? | |
| Where does it live, and is it private? | |

<details>
<summary>Reference: roughly what the saved skill should say (compare after saving, don't paste it in)</summary>

```markdown
---
name: bug-repro-report
description: Turn a raw user bug report into a developer-reproducible report (steps, expected/actual, frequency, environment, failing test, likely cause, open questions). Use when asked to write up, triage or format a bug report.
---

# Bug repro report

Write a report a developer can reproduce from alone.

## Sections (in order)
1. **Title**: the symptom, one line.
2. **Steps**: numbered, from app launch, exact inputs.
3. **Expected** / **Actual**
4. **Frequency**
5. **Environment**: device, OS/API, build/commit, time zone + date when dates matter.
6. **Failing test**: name + branch, if known.
7. **Likely cause**: label it "verified by running" or "unverified".
8. **Questions for the reporter**: only the ones still open.

## Rules
- Never invent facts. Missing information becomes "Unknown – ask reporter" plus a question in section 8.
- Don't propose a fix.
- Under 250 words, GitHub markdown.
```

</details>

---

## Part C: test the skill on a known answer, then on a new ticket

Lesson 3's habit applies to skills too: test on a task where you already know the answer before trusting it.

**Run 1, known answer (#3).** In a **new chat**, so the skill has to load by itself, paste:

```text
Write up this bug report for the developers.

Reporter: "I was looking at my list this morning and a task showed up in red as Overdue, but I'm pretty sure I'm not late on it yet. Kind of stressful! Using the app on my Pixel."

What I found: on a clean clone of main (e113532), Pixel_9_Pro emulator, Android 16 / API 36, time zone Asia/Ho_Chi_Minh, date 2026-11-05. A task due today (2026-11-05) shows "Overdue · Due 2026-11-05" in red. Due yesterday shows Overdue (correct), due tomorrow shows no label (correct). Happens every time. Failing test: TaskRulesTest > "task due today is not overdue" on branch day-2-issue-3-overdue-repro. isOverdue uses !due.isAfter(today) — confirmed by the test; clock/time zone ruled out.
```

Compare with your posted #3 comment, which is the known answer:

| Check | Pass? | Notes |
|---|---|---|
| Did the skill load by itself? (Is it named in the reasoning?) | | |
| All 8 sections, in order | | |
| Exact dates in yyyy-mm-dd in the steps | | |
| Cause labelled "verified by running" | | |
| Didn't invent anything (app version, extra devices, a fix) | | |
| Under 250 words | | |

**Run 2, new ticket with gaps (#5).** In a **new chat**, give it only the raw report:

```text
Write up this bug report for the developers.

Reporter: "There's an empty row in my task list with no title. I don't remember adding it. Maybe I hit Save by accident? I thought the app didn't let you save without a title."
```

| Check | Pass? | Notes |
|---|---|---|
| Unknown sections say "Unknown – ask reporter" instead of made-up steps | | |
| The questions cover how the row appeared (spaces in the title? an error shown? can it be repeated?) | | |
| Likely cause, if given, is labelled "unverified" | | |

If either run fails a check, **iterate**. Ask Claude to edit the skill (for example, "Update bug-repro-report: if no steps are given, never write steps, ask instead"), save it, re-read it, and re-run the failing case in a new chat.

---

## Part D: project or skill? (one line each)

Lesson point: projects hold the *what* (knowledge), skills hold the *how* (a procedure).

| Thing | Project knowledge or skill? | Why |
|---|---|---|
| The README and the Day 2 phase file | | |
| "How we write a repro" (the 8 sections) | | |
| The list of open backlog issues | | |
| "How we review a PR" (the Day 3 checklist order) | | |

<details>
<summary>Suggested answers</summary>

- **README and Day 2 phase file:** project knowledge. It's facts about *this* app and plan.
- **How we write a repro:** a skill. It's a repeatable procedure that works on any ticket.
- **Open backlog issues:** project knowledge, or better, a connector (lesson 8), because issues change and an uploaded copy goes stale.
- **How we review a PR:** a skill. It's a procedure, and Day 7 will build a team version in `.claude/`.

</details>

---

## Wrap-up

- Lesson 7 done (tick it in the Day 2 phase file, or tell Claude Code): ☐
- Does the "One takeaway" row for lesson 7 in the [Day 2 sheet](exercise-261105-2255-day-02-ticket-analysis.md) still match what you learned? Rewrite it if not.
- One thing the skill got wrong on the first try, and the edit that fixed it:
- Would you use this skill for #4 on Day 3? What would you check first?
