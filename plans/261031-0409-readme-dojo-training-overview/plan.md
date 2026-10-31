---
title: "Update README for Dojo 15-day training"
description: "Rewrite the two-line README into an entry point for the solo Dojo training repo."
status: in-progress
priority: P2
effort: "30m"
tags: ["docs", "readme", "dojo"]
created: 2026-10-31
---

# Update README for Dojo 15-day training

## Overview

The README has two lines: the repo name and "Dojo 15-Day Claude Code Training". Its only job right now is to say what the repo is. It should become the entry point. That means linking the master plan, explaining the solo Developer (mobile, Android) setup, indexing the four training plans in `plans/`, and showing how to track progress.

App build/run instructions are out of scope until the Android app exists. Day 0 (phase-01 of the W1 plan) adds them when it scaffolds the app. The README only reserves a placeholder section for them.

## Dependencies

- Reads from the four Dojo plans in `plans/` (W1, W2, W3, Capstone).
- Day 0 of `261031-0357-dojo-w1-shared-fundamentals` later fills in the "Training app" section.

## Goals

| # | Goal | Priority |
|---|------|----------|
| 1 | README explains what the repo is, the track, and the solo setup | P1 |
| 2 | README links the master plan and indexes the four plans with their checkpoints | P1 |
| 3 | README shows how to track progress with `ak plan` | P2 |

## Phases

| # | Phase | Status |
|---|-------|--------|
| 1 | [Phase 1: Rewrite README](./phase-01-rewrite-readme.md) | In Progress |

## Success Criteria

- [x] README renders on GitHub with every relative link resolving to an existing plan file
- [x] The README content matches the plans (day ranges, checkpoints on Days 3, 6, 11 and 15)
- [x] No app instructions invented before the app exists

<!-- slug: readme-dojo-training-overview -->
