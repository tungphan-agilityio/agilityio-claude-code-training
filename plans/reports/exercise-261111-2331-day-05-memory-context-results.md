# Day 5 results: memory and context

Phase: [phase-02-day-05-memory-context.md](../261031-0358-dojo-w2-dev-core/phase-02-day-05-memory-context.md)

**Outcome.** Day 5 deliverables are done except the memory decision below. `CLAUDE.md` is merged (PR #23). The fresh-versus-primed comparison is written up. `/compact` kept the facts we tested. `/memory` was reviewed. `/rewind` removed the test file.

## Deliverables

| Deliverable | Status | Evidence |
|---|---|---|
| `CLAUDE.md` merged via PR | Done | PR #23, squash-merged. The `/memory` screen shows it loaded as "Project instructions". |
| Reviewed memory entries | Done | See the memory section below. |
| Rewind demonstrated | Done | See the rewind section below. |
| Fresh vs primed comparison notes | Done | `exercise-261111-2259-day-05-fresh-vs-primed.md` |

## Compact

- **Focus used:** `/compact keep the overdue rule details`.
- **Recall test:** after compacting, the session answered both questions correctly.
  - "Which test checks that a task due today is not overdue?" It answered `task due today is not overdue` at `TaskRulesTest.kt:27`.
  - The rule's decision line is `TaskRules.kt:18`. Line 16 is the function signature. Both references are valid.
- **Mistake to avoid:** the first attempt typed the question on the same line as `/compact`. That made the question a compaction instruction, so the session compacted and re-read files but never answered. Run `/compact` alone, then ask the question as a separate message.

## Memory review

The auto-memory index has two entries.

- **`stage-explicit-paths-not-directories`:** still correct. Keep it.
- **`pr-18-must-squash-merge`:** out of date. PR #18 is already merged, so its "how to apply" step is done. Its background about the committed swap file is still useful.

Decision pending: delete it, or shorten it to the background only.

## Rewind

- Claude created `scratch/rewind-test.txt` containing `BAD EDIT`.
- `/rewind` removed the file.
- The empty `scratch/` folder was left behind. It was removed by hand with `rmdir`.
- The repo was clean afterwards, as shown by `git status --short`.

## Unresolved

- Decide whether to delete or shorten the PR #18 memory entry.
- Rewind left an empty folder. Check whether it's a bug in `/rewind` or expected behaviour before you rely on it for cleanup.
