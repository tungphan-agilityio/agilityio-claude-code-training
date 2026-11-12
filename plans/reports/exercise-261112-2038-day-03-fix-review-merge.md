# Day 3 exercise: fix, review, merge (Checkpoint 1)

Phase: [phase-04-day-03-fix-review-merge-checkpoint-1.md](../261031-0357-dojo-w1-shared-fundamentals/phase-04-day-03-fix-review-merge-checkpoint-1.md)

**Outcome.** Three of four checkpoint criteria are met. The fourth is only partly met: the merge with a resolved conflict is done, but the cold review did not reach three actionable comments. I recorded this gap rather than padding the review.

---

## 1. What shipped

| PR | Change | Result |
|---|---|---|
| [#19](https://github.com/tungphan-agilityio/agilityio-claude-code-training/pull/19) | Fix #3: `isOverdue` no longer counts a task due today as overdue. Commits `998040e` (failing test), `6b7f46a` (fix). | Merged, CI green. |
| [#20](https://github.com/tungphan-agilityio/agilityio-claude-code-training/pull/20) | Fix #5: a title of only whitespace is rejected. Commits `a239290` (failing test), `5f9dbb1` (fix), `0e3243b` and `81039e4` (review follow-ups). | Merged, CI green. |
| [#26](https://github.com/tungphan-agilityio/agilityio-claude-code-training/pull/26) | Seeded conflict `seed/conflict-2` merged into a rename PR. One conflict in `TaskRules.kt`. | Merged as `39753c2`, branch deleted. |
| [#27](https://github.com/tungphan-agilityio/agilityio-claude-code-training/pull/27) | Ticks the Day 3 items in the W1 plan. Docs only. | Merged as `2002688`, branch deleted. |

## 2. The conflict (PR #26)

- **Seed.** `seed/conflict-2` is based on an older commit. It renames the local in `isOverdue` to `dueDate` and still has the old `!dueDate.isAfter(today)` check.
- **Merge, not rebase.** Rebasing onto the seed would replay all of `main`, so I used `git merge origin/seed/conflict-2`. The only conflict was in `TaskRules.kt`.
- **Resolution.** I kept `main`'s fix (`deadline.isBefore(today)`) and my name `deadline`. Taking the seed's `dueDate` name would have left the PR diff empty. The tests and lint passed on the resolved code.

## 3. The cold review gap (PR #20)

- **What the review found.** Two actionable comments. First, the ViewModel had no test for a whitespace-only title. Second, the rule test covered only spaces, not tabs or newlines. The first was fixed in `0e3243b` and the second in `81039e4`.
- **Checklist items.** Correctness, readability and security basics had no issue. Scope had no unrelated edits.
- **Why it's short.** The review body says it looked for a third issue and did not find a real one. It declined to post a made-up comment.
- **Post-review check.** I looked for a third issue after the merge. The one real gap is small: the ViewModel test does not assert that the form stays open after a rejected save. I did not post it, because PR #20 was already merged and the comment would only fill the checklist.

## 4. Checkpoint status

| Criterion | Status |
|---|---|
| Reproduce the assigned bug and write the repro | Met (Day 2, comment on #3) |
| Fix on a branch with a test | Met (#19, #20) |
| Open a PR with a self-contained description | Met (#19, #20, #26) |
| Cold review with 3 actionable comments, and one merge with conflict resolved | **Partly met.** The merge with resolved conflict is met (#26). The cold review has 2 actionable comments, not 3. |

The phase file says: "If failed: repeat the exercise as paired homework on Days 4-5." This gap may or may not count as a fail. That is your call.

## 5. Left unticked on purpose

- "Draft PR description with Claude, then hand-edit": I can't verify from the repo how the #19 body was written.
- The cold-review checkpoint line and the 3-comment deliverable: not met, see section 3.

## Decisions

- **Cold review counts as not met.** The checkpoint says "3 actionable comments", and the review has 2. A strict reading is the only honest one, so the checkpoint is not passed. The phase rule then applies: repeat the cold review as paired homework on Days 4-5, or as a solo exercise on a seeded PR with real findings.
- **Do not post the nit on #20.** PR #20 is merged, and a comment now would only fill the checklist. Save the nit for the homework review, where it can be a real finding on a fresh PR.
- **PR #19 description box stays unticked.** I can't verify how the body was written. Tick it only if you drafted it with Claude.

- **Outcome: Checkpoint 1 failed on the cold-review criterion.** The fix, PR, conflict and merge criteria passed. The phase rule applies: repeat the cold review as paired homework on Days 4-5.

## Open

- **Retake:** run the cold review again on a PR with three real actionable findings, in a fresh session. Retake target still to be chosen.
- **Earlier homework target (not met):** PR #25 [#25](https://github.com/tungphan-agilityio/agilityio-claude-code-training/pull/25)**, `feat: add title search to the task list (#6)`. It is open, has no reviews, and changes 73 lines across the rules, view model, list screen and tests. Run the cold review in a fresh session that gets the PR description and diff only. It needs at least three real actionable comments.
- Confirm whether the PR #19 description was drafted with Claude.
