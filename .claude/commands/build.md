---
description: Implement an approved plan on agent/<issue>, test it, and draft the PR
argument-hint: <issue-number>
---
Build issue #$ARGUMENTS from `docs/plans/$ARGUMENTS.md`.

1. If the plan is missing or its status is not "approved", stop and tell me.
2. Create branch `agent/$ARGUMENTS` from an up-to-date `main`.
3. Use the test-writer subagent to write the tests from the plan's test plan. Confirm the new tests fail.
4. Implement exactly the plan's steps. Stay inside its file list; if you must go outside it, stop and ask.
5. From `android-app/`, run `./gradlew testDebugUnitTest`, `./gradlew lintDebug`, `./gradlew assembleDebug`. All three must pass.
6. Commit in small conventional commits (`test:`, `fix:`, `feat:`), staging explicit file paths.
7. Use the reviewer subagent on the branch and fix any blockers.
8. Draft the PR body from `.github/pull_request_template.md`, including "What I did not do".
9. Show me the commits and the PR body. Do not push or open the PR until I say so (gate G2 is mine).
