---
title: "Phase 1: Rewrite README"
status: done
---

# Phase 1: Rewrite README

## Overview

Replace the two-line `README.md` with a concise entry point for the training repo. Target length is about 60–90 lines. The content is drawn from the master plan (https://dojo.agilityio.dev/index.html) and the four plans in `plans/`.

## Requirements

- [x] Only `README.md` is touched. No other files are created.
- [x] Facts come from the plans and the master plan. Nothing is invented about the app (it doesn't exist yet).
- [x] Relative links point to the real plan directories.

## Implementation Steps

1. **Title and summary.** One paragraph: the Dojo 15-day Claude Code training, run solo on the Developer track (mobile, Android). It links the master plan, the [schedule](https://dojo.agilityio.dev/schedule.html) and the [developer track](https://dojo.agilityio.dev/developer.html). It notes that feature names were verified by Dojo on 2026-10-07 and should be re-checked weekly.
2. **Program table.** Four rows (stage, days, plan link, checkpoint):
   - W1 Shared fundamentals, Days 0–3 → `plans/261031-0357-dojo-w1-shared-fundamentals/plan.md`. Checkpoint 1 on Day 3.
   - W2 Developer core, Days 4–6 → `plans/261031-0358-dojo-w2-dev-core/plan.md`. Checkpoint 2 on Day 6.
   - W3 Extend, automate, review, Days 7–11 → `plans/261031-0358-dojo-w3-dev-extend-automate-review/plan.md`. Checkpoint 3 on Day 11.
   - Capstone, Days 12–15 → `plans/261031-0358-dojo-capstone/plan.md`. Final "Company-ready" checkpoint on Day 15.
3. **Solo setup.** Short bullets. You play every role (lead/client, BA, QA, dev). Separate Claude sessions stand in for the client, a partner and the peer reviewer. Tickets are GitHub issues on `tungphan-agilityio/agilityio-claude-code-training`.
4. **Training app.** A placeholder section saying an Android app (Kotlin, Jetpack Compose, Gradle, GitHub Actions CI) is built on Day 0, with "build/run instructions added on Day 0". It links the Day 0 phase file.
5. **Tracking progress.** Show `ak plan status plans/<dir>`, `ak plan kanban plans/<dir>` and `ak plan list`. Say that checkboxes in the phase files are the source of truth.
6. **Repo layout.** A tiny tree: `plans/` (training plans and `reports/` for exercise notes and screenshots), plus the app module and `.claude/` (team skills, commands, hooks from Days 7–9) as they appear.
7. **Ground rules.** Taken from the master plan: sandbox repo only (never client code), never paste secrets or customer data, treat AI output as a proposal until you've run it, one ticket per branch, and every change goes through a PR.

## Todo

- [x] Draft README sections 1–7
- [x] Check every relative link exists (`ls` each target)
- [x] Preview the rendered markdown (for example `gh markdown-preview`, or view after pushing)
- [x] Commit on a branch and open a PR (this doubles as Git block practice): PR #1

## Success Criteria

- README is about 90 lines or fewer, all links resolve, and the content matches the plans.
- A reader with no context can tell from the README alone what the repo is, where the plan is, and how to track progress.

## Risk

- Plan directory names may change if plans are archived or renamed. Links would break. Mitigation: re-run the link check whenever plans are moved.
