# Claude 101 lessons 1–6: hands-on practice

Phase: [phase-02-day-01-mental-model-claude-basics.md](../261031-0357-dojo-w1-shared-fundamentals/phase-02-day-01-mental-model-claude-basics.md) · Course notes: [course-summary-261101-2107-claude-101.md](course-summary-261101-2107-claude-101.md)

**How to use this sheet**
- Do each practice in the Claude Desktop app or on claude.ai, right after reading the lesson.
- Paste the prompts as they are, then fill in the notes table.
- The only material you'll share is public training content: the README, the course summaries and the GitHub issue text. Don't upload source files from `android-app/`, because Days 2–3 are where you find the bugs yourself.
- Some features depend on your plan, for example Cowork. If one isn't available, write "not on my plan" and move on.

---

## Lesson 1: What is Claude? Search box or thinking partner?

**In-lesson exercise, "Sort six requests" (done).** The course's result:

| A search box handles these | These need Claude, and you |
|---|---|
| "What's the exchange rate from US dollars to euros today?" | "Rewrite my update below so the delay reads as a decision, not an apology." |
| "What's the difference between a purchase order and an invoice?" | "best structure for a quarterly business review presentation" |
| "How many working days are there between March 3 and March 24?" | "I've pasted a customer's complaint thread below. Help me work out what they're actually asking for before I reply." |

The close calls are the ones where the wording misleads:
- "best structure for a quarterly business review presentation" is typed like a search query, but the useful answer depends on *your* business and audience. It needs a thinking partner.
- "How many working days…" sounds like work, but it's one lookup or calculation, and a search box or calendar handles it.

The test is whether the answer depends on your context and judgment, not whether the request sounds complicated.

**Practice:** sort these requests before asking Claude anything, then check two of them in a chat.

| # | Request | Your call (search / partner) | Why |
|---|---|---|---|
| 1 | "What's the latest stable Android Gradle Plugin version?" | Search | One fact with one right answer. It also changes often, so the official release notes beat Claude's memory, which has a training cutoff (see the AI capabilities course on knowledge). |
| 2 | "Our task app marks a task overdue on its due date. Is that a bug or a product decision, and who should decide?" | Partner | No single fact answers it. It depends on what users expect, what the spec or ticket says, and who owns product decisions, so it needs back-and-forth with your context. |
| 3 | "What's the keyboard shortcut to reformat code in Android Studio on Mac?" | Search | One fixed lookup. A search or the IDE's keymap settings answer it faster than a conversation. |
| 4 | "Help me plan how to split Day 2's five ticket analyses into a 2-hour block." | Partner | The answer depends on your tickets, your pace and the rest of Day 2, and you'd adjust the plan as Claude asks questions. |

Then take one request you sorted as "partner" to Claude, and follow up twice based on its answers.

**Conversation: request #4, run with Claude (in Claude Code, Opus)**

> **Me:** Help me plan how to split Day 2's five ticket analyses into a 2-hour block. Each analysis is 3 sentences (what is asked, what is ambiguous, first step), plus clarifying questions. I also have to compare my analysis with an independent one from a fresh Claude session.

> **Claude:** Do your own thinking first, so Claude's output has something to be compared against:
>
> | Time | Block | Output |
> |---|---|---|
> | 0:00–0:10 | Read all 7 open issues once; pick 5 | A shortlist |
> | 0:10–0:50 | **Solo, no Claude:** 3-sentence analysis per ticket, about 8 min each | 5 draft analyses, your baseline |
> | 0:50–1:20 | **Session A:** paste each ticket plus your draft; ask "what's ambiguous that I missed? Draft clarifying questions for the reporter." | Gaps and questions per ticket |
> | 1:20–1:45 | **Session B (fresh chat, tickets only):** ask for its own 3-sentence analyses, then diff against yours | A list of what B saw that you didn't, and the reverse |
> | 1:45–2:00 | Merge into final analyses and questions; save under `plans/reports/` | The Day 2 deliverable |

> **Me (follow-up 1):** Which 5 of the 7 issues should I pick?

> **Claude:** Pick for *ambiguity*, not ease, since finding gaps is the point of the exercise:
> - **All three bugs (#3, #4, #5).** One becomes the afternoon's reproduction, so analysing it in the morning pays off twice.
> - **#9, "Make the list less confusing".** It's the vaguest: no definition of "confusing" or "clearer".
> - **#7, "App forgets my tasks".** It looks like an enhancement but hides a scope question: save to the device only, or sync?
>
> Skip #6 (search) and #8 (overdue count) for now. They're the clearest requests, so they teach the least.

> **Me (follow-up 2):** What if I run over time?

> **Claude:** Cut Session B down to 3 tickets, the ones where Session A found the most gaps. **Don't cut the solo block.** Without your own baseline you can't tell whether Claude found real gaps or just produced plausible-sounding questions, which is the discernment habit from both earlier courses. Also stop at 3 clarifying questions per ticket; more than that is usually padding.

| | Notes |
|---|---|
| Which one was a closer call than it looked? | #1, the AGP version. It sounds like something to ask Claude, but it's one fact that changes often, so the release notes beat a model with a training cutoff. |
| Did the follow-ups change the direction? How? | Yes. Follow-up 1 changed *how* to choose tickets, from "which are easiest" to "which are most ambiguous", and it tied the morning work to the afternoon's reproduction. Follow-up 2 set a priority for cuts: protect the solo baseline and cut the comparison first. Neither was in the first answer. |
| Lesson reflection: 2–3 tasks from your week that suit a thinking partner | (1) Turning vague tickets like #9 into clarifying questions on Day 2. (2) Writing a PR description from a diff, then editing it myself, on Day 3. (3) Planning which of my two parallel worktree sessions does what, on Day 6. |

---

## Lesson 2: Your first conversation, using the prompt framework

**Practice A: stage, task, rules.** Attach the repo's `README.md`, then send:

```text
I'm a mobile developer doing a 15-day Claude Code training program solo. The attached README describes my training repo and its small Android app, "Dojo Tasks".

Explain to me, as if onboarding a new teammate, how the repo is organized and how I'd build and test the app.

Rules: max 8 bullets, plain language, commands in code blocks, and if the README doesn't say something, say "not in the README" instead of guessing.
```

**Practice B: iterate.** Try all three moves on that answer:
1. **Follow-up:** "Expand on the CI bullet."
2. **Feedback:** "Good, but too formal; make it read like a Slack message."
3. **Edit and resend:** click the pencil icon on your first prompt, change "max 8 bullets" to "max 4 bullets", and resend it.

**Practice C: personalize.**
- Open *Settings > Account > Instructions for Claude* and add a line like "I'm an Android developer (Kotlin, Compose). Prefer concise answers; mark assumptions."
- Open *Settings > Memory* and note whether it's on and what it holds.

**Conversation: Practices A and B, run with Claude (in Claude Code, Opus, with the README as the attached context)**

> **Me:** *(Practice A prompt above)*

> **Claude:**
> - **What it is:** a solo run of the 15-day Dojo Claude Code training (Developer track, Android). You play every role; separate Claude sessions stand in for the client, partner and reviewer.
> - **Plans:** `plans/` has one plan per stage (W1, W2, W3, Capstone). Each day is a phase file, and its checkboxes are the source of truth for progress.
> - **Notes:** exercise write-ups, screenshots and reviews go in `plans/reports/`.
> - **The app:** "Dojo Tasks", a Kotlin, Jetpack Compose and Material 3 to-do app in `android-app/`. It has a list, a detail screen and an add/edit form; data is in memory and resets on restart.
> - **Set-up:** JDK 17+ and Android SDK platform 37 (Android Studio installs both). Open `android-app/` in Android Studio, and add `local.properties` with `sdk.dir=…` if it's missing.
> - **Build and test:**
>   ```bash
>   cd android-app
>   ./gradlew testDebugUnitTest   # unit tests
>   ./gradlew lintDebug           # lint
>   ./gradlew assembleDebug       # debug APK
>   ./gradlew installDebug        # onto a running emulator/device
>   ```
> - **CI:** GitHub Actions runs the tests, lint and a debug build on every PR and on pushes to `main`.
> - **Rules:** sandbox only, no secrets, one ticket per branch, a test with every fix, everything through a PR. How to set up an emulator or which device to use: *not in the README*.

> **Me (follow-up):** Expand on the CI bullet.

> **Claude:** The workflow is `.github/workflows/android-ci.yml`. It runs on every pull request and on every push to `main`, and it does three things: the unit tests (`testDebugUnitTest`), Android lint (`lintDebug`) and a debug build (`assembleDebug`). So a PR whose tests fail, or that breaks the build, shows a red check before you merge. Which JDK the CI uses, how long it takes, whether it keeps reports, and whether the check is *required* before merging (branch protection): *not in the README*.

> **Me (feedback):** Good, but too formal; make it read like a Slack message.

> **Claude:** hey! quick tour of the training repo 👋 `plans/` = the 15-day plan, one file per day, tick the boxes as you go. the app is in `android-app/` (Kotlin + Compose to-do app). `cd android-app && ./gradlew testDebugUnitTest` runs the tests, `installDebug` puts it on the emulator. CI runs tests + lint + build on every PR, so wait for the green check before merging 🚦

> **Me (edit and resend, with "max 4 bullets"):** *(same prompt)*

> **Claude:**
> - **Repo:** a solo 15-day Claude Code training; `plans/` holds one plan per stage and a phase file per day (the checkboxes track progress), and notes go in `plans/reports/`.
> - **App:** "Dojo Tasks", an in-memory Kotlin/Compose to-do app in `android-app/`. You need JDK 17+ and Android SDK 37.
> - **Build and test:** `cd android-app`, then `./gradlew testDebugUnitTest`, `lintDebug`, `assembleDebug`, or `installDebug`.
> - **CI and rules:** every PR runs tests, lint and a build; one ticket per branch, a test with every fix, and no secrets.

| | Notes |
|---|---|
| Did it stick to the README or invent anything? | It stuck to the README. Twice it said "not in the README" instead of guessing: emulator setup in A, and the CI's JDK and branch protection in the follow-up. That came from the rule in the prompt. |
| Follow-up vs. feedback vs. edit-and-resend: which felt most useful? | **Edit-and-resend.** It gave a clean answer with no leftover context from the longer version; four bullets fit a quick onboarding. The follow-up was best at adding depth, and its "not in the README" list surfaced a real gap: is the CI check required before merging? The feedback ("Slack style") changed tone well but dropped detail, such as lint and the SDK requirement. |
| What you put in "Instructions for Claude" | "I'm an Android developer (Kotlin, Compose). Prefer concise answers; mark assumptions." The last part makes the Day 1 lesson ("don't invent facts; mark assumptions") a default for every chat. |
| Memory: on or off; anything surprising saved? | **On.** "Generate memory from chats" is enabled, while "Search and reference chats" and "Include sensitive topics" are disabled. It holds a Profile (role and platform), Recent Work, and two Areas created about 13 hours ago from the Day 1 exercise chats: **Claude Code Training** ("solo, Android track; built Dojo Tasks; reports to team lead") and **Dojo Tasks** (Kotlin/Compose to-do app). **Surprise:** it picked up "reports to team lead" from the Q3 status-update prompt with no explicit "remember this", so even drafting prompts feed memory. Each entry is editable in *Settings > Memory*, and that's the place to check after sharing anything sensitive. **Effect on Lesson 5:** with chat search off, a new chat can't read old chats, but memory can still make it "know" the training context. That would come from memory, not from shared chat history. |

---

## Lesson 3: Getting better results, with a mini eval on a known answer

Lesson 3's key idea is to test Claude on a task where *you already know the answer*. You know how each issue is labelled on GitHub, so start a **new chat** without the labels:

```text
Classify each of these user reports from a to-do app as "bug" or "enhancement". For each, give a one-line reason and your confidence (high/medium/low). Output a table.

1. "Overdue label looks wrong": A task showed up in red as Overdue, but I'm pretty sure I'm not late on it yet.
2. "Editing a task makes a copy": I changed the title of one of my tasks and now I have two of them.
3. "Blank task in my list": There's an empty row with no title. I thought the app didn't let you save without a title.
4. "Search tasks": When the list gets long it's hard to find things. Can we have search?
5. "App forgets my tasks": Every time I reopen the app my tasks are gone and it's back to the sample ones.
6. "Show overdue count in the header": The header says how many are remaining. It would help to also see how many are overdue.
7. "Make the list less confusing": The list order is confusing to me. Can it be clearer?
```

**Compare** with the GitHub labels (`gh issue list`): reports 1–3 are labelled bug (issues #3–#5), and 4–7 are labelled enhancement (issues #6–#9). Then **fix one miss or weak spot** using a technique from the lesson's table: add context, show the format, or ask for its confidence. Re-run the prompt and compare again.

<details>
<summary>Run 1 result (fresh chat in the Claude app)</summary>

| # | Report | Type | Reason | Confidence |
|---|---|---|---|---|
| 1 | Overdue label looks wrong | Bug | Overdue status should reflect the due date, and a likely cause is an off-by-one or timezone error (e.g., comparing a date to a timestamp). | Medium |
| 2 | Editing a task makes a copy | Bug | An edit should update the task in place, but it inserted a new one, probably because the ID was not preserved. | High |
| 3 | Blank task in my list | Bug | It breaks the app's own rule against saving without a title, so some path skips validation (blank or whitespace-only titles, or a different save path). | High |
| 4 | Search tasks | Enhancement | This asks for a new feature, not a fix to existing behavior. | High |
| 5 | App forgets my tasks | Bug | Tasks should persist, but they reset to the sample data, so either nothing is saved or the seed data overwrites stored tasks on launch. | High |
| 6 | Show overdue count in header | Enhancement | It adds new information to a header that already works correctly. | High |
| 7 | Make the list less confusing | Enhancement | It asks for a UX change, and there's no clear expected behavior being violated. | Low |

Assumptions it listed: for #1, the user's "pretty sure" is accurate, so check the due date first. For #3, the app is supposed to enforce a required title. For #7, there's no defined sort order; if there is one and it's inconsistent, this becomes a bug, so ask the reporter what order they expected.

</details>

| | Notes |
|---|---|
| Matches out of 7 | **6 of 7.** The only miss is report 5 (issue #7), which Claude called a bug and GitHub labels an enhancement. |
| Where it disagreed with the labels, and was it *wrong* or arguably right? (Hint: report 5, "App forgets my tasks", could go either way.) | **Report 5 is arguably right from the user's side but wrong for this app.** The README says "Data lives in memory and resets when the app restarts". That's by design, so saving tasks is a feature that hasn't been built yet, not a broken one. Claude didn't have that context and guessed that tasks "should persist". **It said "High" confidence on its one miss**: the loose confidence calibration from the AI capabilities course. **Good:** "Low" confidence on report 7, and a useful flag that report 7 becomes a bug if a sort order is defined. That's a clarifying question for Day 2. **Caution:** the *cause* guesses in the Reason column (ID not preserved, whitespace titles, timezone) are hypotheses. Don't treat them as findings; Day 2 is for checking them by reproducing. |
| Which fix you applied, and did it change the result? | **Add context**, the lesson's fix for "too generic". Run 2 added one product-context line before the list (see below). **Result: 7 of 7.** Report 5 flipped to Enhancement ("losing them on restart is expected"). Other changes: the reasons guess less about causes (report 2 no longer guesses "ID not preserved"), the report 3 assumption disappeared, and report 1 now suggests *how to check it* (reproduce with tasks due today and tomorrow) instead of only naming possible causes. One sentence of context fixed the miss and made the whole answer more careful. |
| Would you delegate ticket triage to Claude on Day 2? With what checks? | **Yes, as a first pass:** 6 of 7 with no context. Checks: (1) give it the product facts (the README and what's by design); (2) ignore its confidence labels and verify every "High" against the spec; (3) treat cause guesses as things to test, not answers; (4) a human decides the final label. |

**Run 2 (the fix): add context.** In a **new chat**, paste the same prompt with this line added before the list:

```text
Product context: this is a training app that, by design, keeps tasks in memory only. Data resets when the app restarts, and saving tasks between launches hasn't been built yet. Classify against what the app is designed to do today.
```

Expected: report 5 becomes "enhancement". Note whether anything else changes.

<details>
<summary>Run 2 result (new chat, with the product-context line)</summary>

| # | Report | Classification | Reason | Confidence |
|---|---|---|---|---|
| 1 | Overdue label looks wrong | Bug | A task marked Overdue before its due date points to a wrong date comparison, such as a timezone issue or an off-by-one at the day boundary. | Medium |
| 2 | Editing a task makes a copy | Bug | Editing should update the existing task, but it inserts a new one instead. | High |
| 3 | Blank task in my list | Bug | Title validation is being bypassed, so something saves a task the app is meant to reject. | High |
| 4 | Search tasks | Enhancement | This asks for a feature the app doesn't have yet. | High |
| 5 | App forgets my tasks | Enhancement | The app is designed to keep tasks in memory only, so losing them on restart is expected. Saving tasks between launches would be new work. | High |
| 6 | Show overdue count in the header | Enhancement | The header's remaining count works as designed, and this asks for a new piece of information. | High |
| 7 | Make the list less confusing | Enhancement | This is a vague UX request with no defined wrong behavior. | Low |

Assumptions it listed: for #1, it assumes the report is accurate, but the user might have the due date wrong, so reproduce with a task due today or tomorrow. For #7, it becomes a bug if the list is meant to follow a defined order and doesn't, so ask the user what order they expected and check it against the sort spec.

</details>

---

## Lesson 4: Desktop app: Chat, Cowork, Code

**Practice A: Chat quick entry and a screenshot.**
- Double-tap **Option** to open quick entry.
- Share a screenshot of any app screen, for example the Dojo Tasks list on the emulator. One is saved at `/private/tmp/claude-501/-Users-nova-dev-agility-claudecode-training-tungphan-agilityio-claude-code-training/7eb3f6ec-840e-4ad9-a6bd-0ba273afe5b0/scratchpad/list.png`.
- Ask: "What would a first-time user find confusing on this screen?" Then follow up once.

**Practice B: a Cowork hand-off**, if it's on your plan.
- Create an empty folder such as `~/dojo-cowork-out`. **Don't point Cowork at the repo**: it saves files into the folder it's given.
- Copy the three course summaries from `plans/reports/` into that folder, then hand off:

```text
Read the three course summaries in this folder and produce one file, "day-1-study-sheet.md": the 10 most practical habits across all three courses, each with the course it came from, grouped by the 4Ds. Ask me any scoping questions before you start, and show me your plan.
```

**Practice C: just look at the Code tab.** Open it, pick **Local**, choose the repo folder, set the mode to **Plan** and ask: "Describe this repo's structure. Don't change anything." Day 4 covers Claude Code properly.

| | Notes |
|---|---|
| Quick entry: useful mid-task, or in the way? | |
| Cowork: did it ask scoping questions or show a plan? Was the output usable? | |
| Code tab, Plan mode: did it stay read-only? | |
| Reflection: one whole task you've been feeding in question by question | |

---

## Lesson 5: Introduction to projects

**Practice:** create a project named **"Dojo Training"**.
- **Instructions:**

  ```text
  I'm an Android developer (Kotlin, Jetpack Compose) doing a 15-day Claude Code training solo. Answer concisely, mark assumptions, and when a question touches the course material, say which course summary the answer comes from.
  ```
- **Knowledge:** upload `README.md` and the three `plans/reports/course-summary-*.md` files. The names are already descriptive.
- **Chat 1:** "Which habits from the three courses should I apply on Day 2, ticket analysis?"
- **Chat 2:** a *new* chat in the same project. Ask: "What did I ask you in my last chat?" This tests the lesson's point that chats don't share context, only the knowledge base does.

| | Notes |
|---|---|
| Did Chat 1 cite the right summary? | |
| Chat 2 result: did it know your previous chat? (Memory may blur this, so note whether Memory is on.) | |
| What else would you add to this project's knowledge later? | |

---

## Lesson 6: Creating with artifacts

**Practice:** in the Dojo Training project, ask for a deliverable, not just content:

```text
Create an artifact: a one-page doc titled "Day 1 recap" for my team lead, with sections Done / Learned (3 bullets, one per course) / Next (Day 2). Keep it under 200 words.
```

Then change it all three ways:
1. **Edit it directly:** fix one word yourself.
2. **Comment for Claude:** comment on the "Learned" section with "make these more concrete".
3. **Just ask:** "Add a one-line link to my GitHub repo under Next."

Optionally, make an interactive artifact: "Build an interactive quiz with 8 questions on the 4Ds and the four AI properties, using the course summaries."

Finally, open the **Artifacts** tab to confirm it's saved there. Check the **Share** options without sharing anything.

| | Notes |
|---|---|
| Artifact type you got (Doc, or a plain artifact) | |
| Edit vs. comment vs. ask: which was fastest for what? | |
| Share options available on your plan | |

---

## Wrap-up

- Lessons done (tick them in the Day 1 plan, or tell Claude Code): 1 ☑ 2 ☑ 3 ☑ 4 ☐ 5 ☐ 6 ☐
- One feature you'll keep using from tomorrow:
- One thing that didn't work as the lesson described:
