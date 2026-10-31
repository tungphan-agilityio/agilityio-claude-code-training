---
title: "Phase 1: Day 0: Training App Scaffold"
status: todo
---

# Phase 1: Day 0: Training App Scaffold

## Overview

Create the Android training app in this repo so every day's exercise has a sandbox target (never client code).

Source: [index.html](https://dojo.agilityio.dev/index.html)

## Todo

- [ ] Scaffold Android app: Kotlin, Jetpack Compose, Gradle wrapper, single `app` module
- [ ] Implement 2-3 small screens (e.g. list + detail + form) with a ViewModel and repository layer
- [ ] Add JUnit unit tests and `./gradlew test` / `./gradlew lint` passing
- [ ] Add GitHub Actions CI running build, lint and unit tests on PRs
- [ ] Push `main` to origin (tungphan-agilityio/agilityio-claude-code-training, issues enabled)
- [ ] Seed 5+ backlog tickets as GitHub issues (mix of bugs, features, vague requests); write them in a separate Claude session acting as reporter so you analyze them cold on Day 2
- [ ] Seed 2-3 deliberate bugs, at least one expressible as a failing unit test (Day 2/3 target)
- [ ] Prepare a seeded merge-conflict branch for Day 2 and another for Day 3
- [ ] Prepare a seeded-buggy branch for Day 10 ultrareview comparison
- [ ] Update README with build/run instructions

## Exercise

Build and run the app on an emulator; confirm CI is green on `main`.

## Deliverables

- [ ] Android app source in repo
- [ ] CI workflow `.github/workflows/`
- [ ] Seeded issues, bug list (stored outside the repo so you don't peek), conflict branches

## Notes / Skills

Implement via `/ak:cook` on this phase. Android skills: android-planning, android-networking.

## Success Criteria

All todo items and deliverables done.
