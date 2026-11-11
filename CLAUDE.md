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
