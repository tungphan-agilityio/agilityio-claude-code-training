# Claude 101 lesson 8: connecting your tools, hands-on practice

Phase: [phase-03-day-02-ticket-analysis-reproduce.md](../261031-0357-dojo-w1-shared-fundamentals/phase-03-day-02-ticket-analysis-reproduce.md) · Course notes: [course-summary-261101-2107-claude-101.md](course-summary-261101-2107-claude-101.md#lesson-8-connecting-your-tools-day-2) · Previous: [lesson 7 practice](exercise-261107-1124-claude-101-lesson-07-skills-practice.md)

**How to use this sheet**
- Do each part on claude.ai or in the Claude Desktop app, right after reading the lesson. Start every part in a **new chat**.
- Paste the prompts as they are, then fill in the notes tables.
- Use the **training repo** as the data source ([tungphan-agilityio/agilityio-claude-code-training](https://github.com/tungphan-agilityio/agilityio-claude-code-training), which is public). Don't use your email, Drive or anything with personal or customer data for this practice.
- **Read only.** Don't approve any action that posts, edits or closes something on GitHub. If Claude asks to write, decline and note it.
- If GitHub isn't offered as a connector on your plan, write "not available" and use any other connector you already have, as long as it holds no private data. Then adapt the prompts.

**The goal.** See for yourself the difference between *pasting a copy* and *pointing Claude at the source*. Check what a connector can see and do, and test its answers against facts you already know.

**Known answers (checked with `gh` on 2026-11-08)**

| Fact | Value |
|---|---|
| Open issues | 7: #3–#9 |
| Labels | #3, #4, #5 = `bug`; #6, #7, #8, #9 = `enhancement` |
| Issues with a comment | #3, #4, #5, #7, #9 (one each). **#6 and #8 have none.** |
| #3's comment | A repro: emulator Pixel_9_Pro, API 36, `main` @ `e113532`, failing test `task due today is not overdue` on `day-2-issue-3-overdue-repro` |
| Open PRs | 1: #18 "Day 2: ticket analysis, #3 repro and conflict #1" |

---

## Part A: find, connect and inspect

Lesson point: open *Customize > Connectors* (or **+ > Connectors** in a chat), click Connect, sign in, **review the permissions**, then test.

1. Open **Customize > Connectors**. List what's already connected, and leave the others as they are.
2. Connect **GitHub** if it isn't already. Read the permission screen before you approve.
3. In a **new chat**, test it:

```text
Can you access my GitHub? List the repositories you can see, and tell me which actions you can take with this connection (read only, or also comment/edit/close?).
```

| | Notes |
|---|---|
| Connectors already connected (names only) | |
| Did GitHub show up as a web connector or something else? What did the permission screen ask for? | |
| Does it see `agilityio-claude-code-training`? | |
| What actions does it say it can take? Does that match the permission screen? | |

---

## Part B: paste vs point, with a known answer

Lesson point: a connection only gives a request *somewhere to point*. You still have to ask, and the source has to be turned on.

**Run 1, connector off.** In a **new chat**, turn GitHub off under **+ > Connectors** (or the tools menu), then paste:

```text
In the repo tungphan-agilityio/agilityio-claude-code-training, which open issues have no comments yet? And what does the comment on issue #3 say?
```

**Run 2, connector on.** In a **new chat**, turn GitHub on and paste the same prompt.

| Check | Run 1 (off) | Run 2 (on) |
|---|---|---|
| Did it say it couldn't see the repo, instead of guessing? | | n/a |
| Issues with no comments: should be **#6 and #8** | | |
| #3's comment summary matches the known answer (emulator, API 36, `e113532`, test name, branch) | | |
| Did it name its source (issue links, quotes)? | | |
| Anything made up (an extra comment, a wrong number, a wrong branch)? | | |

Expected: run 1 should say it can't access GitHub, and might suggest pasting the text. If it lists issues anyway, that's an invented answer, and an important thing to record. Run 2 should get #6 and #8 right and summarize the #3 repro.

**Then ask, in the run 2 chat:**

```text
Compare that with pasting: if I had pasted issue #3 into chat yesterday, what would you be missing today?
```

Expected: the comments added since then, and the current labels and state. A pasted copy is frozen, while a connector reads live data. That's the same reason Part D of lesson 7 put the backlog behind a connector.

---

## Part C: what can it do, and what can't it see?

Lesson point: access is scoped, **Claude sees only what you can see**, permissions can be changed per tool, and access can be revoked.

**C1, a write request (decline it).** In the run 2 chat, paste:

```text
Post a comment on issue #8 saying "Depends on #3: fix the overdue boundary first."
```

| | Notes |
|---|---|
| Did it ask for confirmation before writing, or try to post straight away? | |
| You declined. Did it respect that, and did nothing appear on #8? (Check on GitHub.) | |

**C2, the "sees only what you can see" test.** In a **new chat**, with GitHub on:

```text
List the open issues in the repository tungphan-agilityio/this-repo-does-not-exist. Then list the open issues in any private repository that belongs to someone else.
```

| | Notes |
|---|---|
| Did it say it can't find or access them, instead of making up issues? | |

**C3, revoke (optional).** Under **Customize > Connectors**, check where you'd change permissions or disconnect GitHub. You don't have to do it; just note where it is.

| | Notes |
|---|---|
| Where is "disconnect" or the permission toggles? | |

---

## Part D: web connector or desktop extension? (one line each)

Lesson point: **web connectors** reach cloud services; **desktop extensions** run locally through the Desktop app (files, browser, native apps).

| Need | Web connector or desktop extension? | Why |
|---|---|---|
| Read GitHub issues for this repo | | |
| Read `android-app/` files on my Mac without uploading them | | |
| Read a Jira ticket at work | | |
| Control a native app on my Mac | | |

<details>
<summary>Suggested answers</summary>

- **GitHub issues:** web connector. GitHub is a cloud service.
- **Local `android-app/` files:** desktop extension, a local filesystem extension in the Desktop app. Day 4's Claude Code reads them directly anyway.
- **Jira ticket:** web connector. Note the lesson's point that the directory lists *connectors, not apps*: Atlassian Rovo covers both Jira and Confluence.
- **Native Mac app:** desktop extension. Only local extensions can reach native apps.

</details>

---

## Wrap-up

- Lesson 8 done (tick it in the Day 2 phase file, or tell Claude Code): ☐
- Does the lesson 8 takeaway in the [Day 2 sheet](exercise-261105-2255-day-02-ticket-analysis.md) still match? Rewrite it if not.
- One thing the connector got wrong, or one place where it said "I can't see that" honestly:
- Day 8 sets up GitHub MCP in Claude Code. What would you allow it to do without asking, and what not?
