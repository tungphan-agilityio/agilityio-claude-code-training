# agilityio-claude-code-training

A solo run of the Dojo 15-day Claude Code training, on the Developer track with the mobile (Android) role. It follows the [master plan](https://dojo.agilityio.dev/index.html), the [day-by-day schedule](https://dojo.agilityio.dev/schedule.html) and the [developer track](https://dojo.agilityio.dev/developer.html). Dojo verified the feature and course names on 2026-10-07, so re-check the linked docs before each week.

## Program

| Stage | Days | Plan | Checkpoint |
|---|---|---|---|
| W1: Shared fundamentals | 0–3 | [dojo-w1-shared-fundamentals](plans/261031-0357-dojo-w1-shared-fundamentals/plan.md) | Checkpoint 1 (Day 3) |
| W2: Developer core | 4–6 | [dojo-w2-dev-core](plans/261031-0358-dojo-w2-dev-core/plan.md) | Checkpoint 2 (Day 6) |
| W3: Extend, automate, review | 7–11 | [dojo-w3-dev-extend-automate-review](plans/261031-0358-dojo-w3-dev-extend-automate-review/plan.md) | Checkpoint 3 (Day 11) |
| Capstone | 12–15 | [dojo-capstone](plans/261031-0358-dojo-capstone/plan.md) | Final "Company-ready" (Day 15) |

Each day is one phase file. Its tasks, deliverables and checkpoint criteria are checkboxes. Day 0 is an extra setup day that builds the training app.

## Solo setup

- I play every role: lead/client, BA, QA and developer.
- A separate Claude session stands in for each missing person: the client, a partner and the peer reviewer.
- Tickets are GitHub issues on [tungphan-agilityio/agilityio-claude-code-training](https://github.com/tungphan-agilityio/agilityio-claude-code-training/issues).

## Training app

A small Android app (Kotlin, Jetpack Compose, Gradle, GitHub Actions CI) is the target for every exercise. It gets built on Day 0 ([phase file](plans/261031-0357-dojo-w1-shared-fundamentals/phase-01-day-00-training-app-scaffold.md)), and the build and run instructions will be added here then.

## Tracking progress

The checkboxes in the phase files are the source of truth.

```bash
ak plan list                                   # all plans and their status
ak plan status plans/<plan-dir>                # progress summary for one plan
ak plan kanban plans/<plan-dir>                # phases as a kanban board
ak plan reindex --apply                        # re-sync after editing plan files by hand
```

## Repo layout

```
.
├── plans/           # training plans (one per stage) and this repo's other plans
│   └── reports/     # exercise notes, screenshots, review write-ups
├── app/             # Android training app (from Day 0)
└── .claude/         # team skills, commands and hooks (from Days 7–9)
```

## Ground rules

- Sandbox only: this repo and its app, never client code.
- Never paste secrets, credentials or customer data into Claude.
- Treat AI output as a proposal until it has been run. Trust tests, runs and logs over fluent answers.
- One ticket per branch, small commits, and a test with every fix.
- Every change goes through a PR with a self-contained description.
