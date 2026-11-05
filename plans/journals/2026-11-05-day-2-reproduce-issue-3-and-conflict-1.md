---
title: "Day 2: reproduce issue 3 and conflict 1"
date: 2026-11-05
summary: "Issue #3 is an inclusive date boundary in TaskRules.isOverdue; failing test written, conflict #1 resolved, push blocked"
---

# Day 2: reproduce issue 3 and conflict 1

## What happened
- A clean clone of main (e113532) builds and its 22 tests pass. On the Pixel_9_Pro emulator (API 36), sample task 3, due today, shows "Overdue" in red.
- Root cause: `TaskRules.isOverdue` uses `!due.isAfter(today)`, which includes today, while its KDoc says "before today". The clock/time-zone and done-flag hypotheses were ruled out by running.
- Added the failing test `task due today is not overdue` on `day-2-issue-3-overdue-repro` (45ea91a). Result: 23 tests, 1 fails.
- `seed/conflict-1` merges cleanly with main by itself, so I made a conflicting local edit and ran `git pull --rebase origin seed/conflict-1`. I kept upstream's wording. During a rebase `--ours` is the upstream side, the reverse of a merge.
- Posted the repro on #3 and clarifying questions on #4, #5, #7 and #9.

## Decision
- Target bug: #3. Its fix is deferred to Day 3.
- Items only the user can do (partner comparison, reporter role-play, Claude 101 lessons 7–13) are left as a worksheet in the report.

## Next steps
- Push `day-2-issue-3-overdue-repro`, `day-2-conflict-1-practice` and `day-2-ticket-analysis`, because session permissions blocked `git push`.
- Do the worksheet, then fix #3 on Day 3.

> Historical work record — not durable authority. Prefer docs/specs/ADRs for current decisions.
