# CLAUDE.md

Guidance for Claude Code in this repo. Keep it short; the README has the full overview.

## Project

Dojo Tasks: a small Android to-do app (Kotlin, Jetpack Compose, Material 3). Gradle project in `android-app/`. Data is in memory and resets on restart.

## Commands

Run from `android-app/`:

```bash
./gradlew testDebugUnitTest   # unit tests (JVM, no emulator)
./gradlew lintDebug           # Android lint
./gradlew assembleDebug       # debug APK
./gradlew installDebug        # install on a running emulator or device
```

CI runs the first three on every PR and on pushes to `main`. A change is ready only when all three pass locally.

## Code layout

Under `android-app/app/src/main/java/com/dojo/tasks/`:
- `data/`: `Task` model and `TaskRepository` (in memory).
- `domain/TaskRules.kt`: pure business rules (overdue, title validation, sorting). No Android imports.
- `ui/`: `TasksViewModel` (state and navigation) and the Compose screens.

## Conventions

- Put business rules in `domain/TaskRules.kt` and test them there. Keep Compose code free of date and validation logic.
- Each fix needs a test that fails before the fix and passes after.
- One ticket per branch. Keep commits small and conventional (`fix:`, `feat:`, `test:`).
- Every change goes through a PR with a self-contained description.

## Never

- Never commit `local.properties` or any secret.
- Never add persistence, network or new dependencies unless a ticket asks for them.
- Never push to `main` directly or merge a PR with failing CI.
- Never paste secrets, credentials or customer data into a prompt.

## Agentic workflow

Work on a ticket moves through files, and Nova approves at two gates:

1. `/spec <issue>`: the `analyst` subagent writes `docs/specs/<issue>.md` (acceptance criteria, edge cases, affected files).
2. `/plan <issue>`: the `planner` subagent writes `docs/plans/<issue>.md` (files, steps, test plan, risk). **Gate G1: stop until Nova approves the plan.**
3. `/build <issue>`: implement on branch `agent/<issue>`, have the `test-writer` subagent write tests from the plan, run the three Gradle checks, then draft the PR from `.github/pull_request_template.md`. **Gate G2: only Nova merges.**

- A plan above ~400 changed lines is split into smaller tickets first.
- Every PR links its plan file and lists what it did not do.
- `.claude/loop.md` is the CI-fix loop for an open PR; use the `reviewer` subagent for a read-only review before marking a PR ready.
- Hooks block edits to `.github/workflows/`, `.claude/settings.json`, `.claude/hooks/`, the Gradle wrapper, `local.properties` and keystores. Ask Nova instead of working around them.
