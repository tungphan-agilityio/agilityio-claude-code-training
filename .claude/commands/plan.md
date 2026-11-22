---
description: Write docs/plans/<issue>.md from the spec, then stop for approval (gate G1)
argument-hint: <issue-number>
---
Use the planner subagent to write `docs/plans/$ARGUMENTS.md` from `docs/specs/$ARGUMENTS.md`.
If the spec is missing, tell me to run `/spec $ARGUMENTS` first and stop.
When the plan is written, show me the file list, estimated size and risks, then STOP.
Do not write code until I say the plan is approved (gate G1).
