Check the pull request for the current branch and keep it ready to merge. Work in this order and stop at the first step that needs me.

1. Find the PR with `gh pr view`. If the branch has no PR, or the branch is `main`, say so in one line and do nothing else.
2. Check CI with `gh pr checks`. If a check failed, read the failing job's log, find the cause, and fix it on this branch. Each fix needs a test that fails before it and passes after it.
3. Read new review comments. Fix the ones that are clear. For any that need a decision, list them and wait.
4. If `main` has moved and the PR has a conflict, merge `main` into the branch and resolve it. Do not rebase.
5. Before you call anything fixed, run all three from `android-app/`: `./gradlew testDebugUnitTest`, `./gradlew lintDebug`, `./gradlew assembleDebug`.

Rules:
- Stay on the PR's ticket. No refactors, cleanup passes or new dependencies that the ticket does not ask for.
- Commit small, with `fix:`, `feat:` or `test:`. Stage explicit file paths, never a directory.
- Never push to `main` and never merge the PR. If `git push` is denied, list the commits that are waiting and ask me to push.
- If nothing changed since the last check, reply with one line, for example "PR #12: CI green, no new comments."
