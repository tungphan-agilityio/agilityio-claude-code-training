---
title: "Phase 2: Day 8: MCP & Connectors"
status: todo
---

# Phase 2: Day 8: MCP & Connectors

## Overview

Connect external tools via MCP so Claude reads real PR/ticket state and can drive the app.

Source: [day-08.html](https://dojo.agilityio.dev/schedule/day-08.html)

## Todo

- [ ] Course: Introduction to model context protocol
- [ ] Add GitHub MCP: `claude mcp add --transport http github https://api.githubcopilot.com/mcp/`; restart, sign in
- [ ] Add Playwright MCP: `claude mcp add playwright -- npx -y @playwright/mcp@latest`; restart

## Exercise

Use one MCP tool end-to-end in a session: query PRs and comment on one.

## Deliverables

- [ ] GitHub + Playwright MCP configured
- [ ] PR comment posted through MCP

## Notes / Skills

Check tools are listed after restart (PR/ticket tools, browser open/click/screenshot).

## Success Criteria

All todo items and deliverables done.
