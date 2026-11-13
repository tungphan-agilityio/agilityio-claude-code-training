# Day 3 exercise: fix, review, merge (Checkpoint 1)

Phase: [phase-04-day-03-fix-review-merge-checkpoint-1.md](../261031-0357-dojo-w1-shared-fundamentals/phase-04-day-03-fix-review-merge-checkpoint-1.md)

**Update 2026-11-13: the retake passed and Checkpoint 1 is now passed. See the Retake section at the end.**

**Outcome on Day 3.** Three of four checkpoint criteria are met. The fourth is only partly met: the merge with a resolved conflict is done, but the cold review did not reach three actionable comments. I recorded this gap rather than padding the review.

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

## Retake (2026-11-13): passed

**Outcome.** The cold review was repeated on a seeded practice PR and produced seven real, actionable comments. The fourth criterion is now met, so Checkpoint 1 is passed.

- **Target.** [#31](https://github.com/tungphan-agilityio/agilityio-claude-code-training/pull/31), `feat: add share summary of tasks`. One Claude session wrote it with six planted defects and kept the answer key outside the repo. CI was green, because the PR's own tests did not catch the defects. `seed/buggy-review` was left alone for Day 10.
- **Review.** A second, fresh Claude session got the PR description and diff only. It judged the PR reviewable from the description alone and posted [one review](https://github.com/tungphan-agilityio/agilityio-claude-code-training/pull/31#pullrequestreview-5893927436) with seven inline comments in checklist order.
- **Check.** I compared each comment with the answer key and with the code. All six planted defects were found. The seventh comment was not planted and is also real.

| # | Area | Where | Finding | In answer key |
|---|---|---|---|---|
| 1 | Correctness | `ShareSummary.kt:16` | `doneCount` counts tasks that are not done, but the header labels it "done". Three tasks with two done prints "1 done". | Yes |
| 2 | Correctness | `ShareSummary.kt:25` | A task without a due date prints "due null". | Yes |
| 3 | Tests | `ShareSummaryTest.kt:20` | The header test only checks `startsWith("2 tasks")`, and its data has one done and one open task, so it cannot catch finding 1. | No, extra |
| 4 | Tests | `ShareSummary.kt:29` | `dueThisWeek` has no test, although it has a 7-day boundary. | Yes |
| 5 | Readability | `ShareSummary.kt:35` | The KDoc says the subject names the overdue count; the code uses the total count. | Yes |
| 6 | Security basics | `ShareSummary.kt:41` | The first task's title goes into the file name unsanitised, so `/` and `..` pass through. | Yes |
| 7 | Scope | `strings.xml:25` | An unrelated wording change to `error_bad_date`. | Yes |

- **One weak suggestion.** Comment 2 proposes reusing the `no_due_date` string resource. `ShareSummary` is in `domain/`, which has no Android imports, so the fix should be plain Kotlin text instead. The finding itself stands.
- **Who reviewed.** A Claude session did this review as the solo stand-in for a peer, the same way as the PR #20 review. I did not review the PR by hand.
- **Cleanup.** PR #31 was closed without merging and its branch was deleted.

The PR #25 homework attempt stays as recorded: one comment, not met. That bug was fixed and #25 was merged on 2026-11-13.
