# Day 5 exercise: fresh session vs primed session

Phase: [phase-02-day-05-memory-context.md](../261031-0358-dojo-w2-dev-core/phase-02-day-05-memory-context.md)

**Task (same prompt in both):** "Where is the overdue rule decided in this app, and which unit tests cover it? Give file and line references and say what each test checks."

**Method.** Both runs were separate non-interactive Claude Code processes (`claude -p`), from the repo root.
- **Session A (fresh):** the prompt, with nothing else.
- **Session B (primed):** a first call that reads `README.md` and `TaskRules.kt` and replies "ready", then the same prompt resumed in that session.

Both sessions still loaded the repo `CLAUDE.md` and memory at startup, so "fresh" means no task context, not no context.

## Results

| | A: fresh | B: primed (prime + run) |
|---|---|---|
| Turns | 4 | 3 + 5 = 8 |
| Cost | $0.163 | $0.128 + $0.188 = $0.316 |
| Input tokens, incl. cache | ~163k | ~77k + ~224k = ~301k |
| Output tokens | 1,507 | 205 + 1,404 = 1,609 |

## Quality

- **Accuracy:** both are correct. Every file and line reference checked against the code matches. Both name `TaskRules.isOverdue` as the single source and list the same six tests.
- **Gaps:** both report no overdue tests in `TasksViewModelTest` and no coverage of the UI call sites. B adds a note that it searched only `app/src`.
- **Difference:** B's answer has slightly more detail, such as the fixed `today` line and the regression test's issue number. It is not more correct.

## Conclusion

For this small question, priming did not pay off. It cost about twice as much, and the answer was no better. The main cost was the priming step, which re-read the README and resumed a larger context. Priming is more likely to help on a larger task, or on repeated questions in the same session, where the cached context is reused.

## Unresolved

- One pair of runs is a small sample. Run the same comparison on a bigger task before drawing a firm rule.
- Both runs used the same `CLAUDE.md` and memory. A version without them would show the true cost of a fully empty start.
