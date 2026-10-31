---
title: "Phase 1: Day 0: Training App Scaffold"
status: done
---

# Phase 1: Day 0: Training App Scaffold

## Overview

Create the Android training app in this repo so every day's exercise has a sandbox target (never client code).

Source: [index.html](https://dojo.agilityio.dev/index.html)

## Todo

- [x] Scaffold Android app: Kotlin, Jetpack Compose, Gradle wrapper, single `app` module
- [x] Implement 2-3 small screens (e.g. list + detail + form) with a ViewModel and repository layer
- [x] Add JUnit unit tests and `./gradlew test` / `./gradlew lint` passing
- [x] Add GitHub Actions CI running build, lint and unit tests on PRs
- [x] Push `main` to origin (tungphan-agilityio/agilityio-claude-code-training, issues enabled)
- [x] Seed 5+ backlog tickets as GitHub issues (mix of bugs, features, vague requests); write them in a separate Claude session acting as reporter so you analyze them cold on Day 2
- [x] Seed 2-3 deliberate bugs, at least one expressible as a failing unit test (Day 2/3 target)
- [x] Prepare a seeded merge-conflict branch for Day 2 and another for Day 3
- [x] Prepare a seeded-buggy branch for Day 10 ultrareview comparison
- [x] Update README with build/run instructions

## Exercise

Build and run the app on an emulator; confirm CI is green on `main`.

## Deliverables

- [x] Android app source in repo
- [x] CI workflow `.github/workflows/`
- [x] Seeded issues, bug list (stored outside the repo so you don't peek), conflict branches

## Notes / Skills

Implement via `/ak:cook` on this phase. Android skills: android-planning, android-networking.

## Success Criteria

All todo items and deliverables done.

## Progress notes (2026-10-31)

- App "Dojo Tasks" lives in `android-app/` (Gradle project root). It was built and run on the Pixel_9_Pro emulator. 22 unit tests pass, `lintDebug` and `assembleDebug` pass locally.
- The 3 planted bugs are verified to reproduce (probe tests in a scratch copy, not committed). The answer key is in `~/dojo-private/seeded-bugs.md`, outside the repo.
- Merged to `main` via PR #2. CI ("Android CI") passed in 2m20s.
- Seeded issues: bugs #3, #4, #5 and enhancements #6, #7, #8, #9.
- Seeded branches pushed: `seed/conflict-1` (Day 2), `seed/conflict-2` (Day 3), `seed/buggy-review` (Day 10).
