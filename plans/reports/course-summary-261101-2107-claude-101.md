# Claude 101: lesson-by-lesson summary

- **Course:** https://academy.claude.com/courses/claude-101 (Anthropic Academy)
- **Taken for:** Dojo Days 1–2. Lessons 1–6 are on Day 1 (first conversation, prompting basics, projects, artifacts); lessons 7–13, the quiz and the badge are on Day 2.
- **Sources:** each lesson page's text, scraped on 2026-11-01. Unlike the other two Day 1 courses, Claude 101 is mostly text lessons. Where a page includes a video with a written transcript (lessons 2, 3 and 5), the transcript is folded in. The Lesson 12 videos have no spoken narration. Interactive exercises (sorting and routing quizzes, connector toggles) are described, not reproduced. These are summaries, not copies.
- **Note:** this course describes product features and plan availability as of the scrape date. They change often, so check the Help Center before relying on a specific setting or menu path.

## The course at a glance

| Module | Lessons | Core idea |
|---|---|---|
| 1. Meet Claude | 1–4 | Claude is a thinking partner, not a search box. Prompt with stage, task and rules, then iterate. Pick the right "shape" of work: Chat, Cowork or Code. |
| 2. Organizing your work and knowledge | 5–7 | **Projects** store knowledge, **artifacts** are the outputs you keep, **skills** encode how to do a task. |
| 3. Expanding Claude's reach | 8–10 | **Connectors** (MCP) bring in your tools, **Enterprise Search** searches your organization's knowledge, **Research** runs multi-source investigations with citations. |
| 4. Putting it all together | 11–12 | Use cases by role. Claude also runs in Claude Code, Claude Tag (Slack), Claude Design, Microsoft 365 and Chrome. |
| 5. Conclusion | 13 | Recap and resources, then the quiz and badge. |

**The prompt framework** (Lesson 2), which is a light form of AI Fluency's *Description*:

| Element | Ask yourself |
|---|---|
| Set the stage | What's my role and objective? What context should Claude know? |
| Define the task | What action: write, analyze, build…? |
| Specify rules | What style, tone, format? Can I attach examples? |

## Module 1: Meet Claude

### Lesson 1: What is Claude? (Day 1)

- Claude is an AI assistant trained with **Constitutional AI**: principles that steer it away from harmful, discriminatory, illegal or unethical help, toward being safe, beneficial and transparent.
- It's more than a chatbot. It's a thinking partner for summarization, search, writing, Q&A, coding and reasoning. It's built to be **steerable**: you can direct its personality, tone and behavior.
- Strengths highlighted:
  - writing;
  - research and analysis, with a context window of 200K+ tokens (about 500 pages), and up to 1M on paid plans with supported models;
  - coding ("one of Claude's greatest strengths");
  - reasoning, with **Thinking** for step-by-step analysis;
  - learning, where **Learning mode** guides your reasoning instead of handing you answers.
- Exercise: sort six requests into "a search box could handle this" or "this needs a thinking partner". A request's wording can hide which one it really needs.
- Ways to use the same intelligence:
  - **claude.ai** (web, desktop and mobile), which is this course's focus;
  - **Claude Code**, for agentic coding;
  - **Claude Tag**, in Slack;
  - **Claude Design**, which turns ideas into interactive prototypes;
  - **Claude for Microsoft 365**.
- Reflection: look at your calendar and pick tasks where a thinking partner would help.

### Lesson 2: Your first conversation with Claude (Day 1)

- Talk to Claude like a coworker: naturally, concisely, conversationally. **Claude brings the intelligence, and you bring the context and expertise.**
- Use the three-part prompt framework (stage, task, rules). The worked example is a marketing lead at an indie streaming startup asking for a market report for a Series A pitch, using web research with citations, in up to 5 pages with set sections. The framework is adapted from the AI Fluency 4D framework.
- **Add context:**
  - Upload files: PDF, DOCX, CSV, TXT and images. Claude reads both text and visuals.
  - Turn on tools: web search, and connectors such as Google Drive.
  - Set standing preferences in *Settings > Account > Instructions for Claude*.
- **Choose a model:** Opus for the most complex, multi-step work. Sonnet is the recommended everyday default, balancing capability and cost. Switching models starts a new chat. Extended thinking helps on hard problems but adds latency; start without it for simple tasks.
- **Iterate:**
  - Ask follow-ups ("expand on point 2").
  - Give feedback ("too formal, make it conversational").
  - Redirect ("I meant X, not Y"), or restart in a new chat to reset the context.
  - Use the pencil icon to edit and resend a prompt.
- **Personalize:**
  - **Memory** saves your role, preferences and past decisions across chats. You can review or delete it in *Settings > Memory*. It's on by default for Free, Pro and Max, and off by default for Team and Enterprise.
  - **Skills** are reusable instructions, covered in Lesson 7.
- Note: the video's "Use style" menu has since been removed.

### Lesson 3: Getting better results (Day 1)

Common problems and their fixes:

| Problem | Cause | Fix |
|---|---|---|
| Too generic | Not enough context | Add the audience, role and constraints. Example: "the second delay; keep it professional but apologetic" |
| Wrong length | Claude is guessing | Say it: "two paragraphs", "under 100 words" |
| Wrong format | It knows *what*, not *how* to present it | Show an example, or spell out the structure |
| Confident but wrong | Plausible fabrication, especially specific facts or niche topics | Verify key facts, ask for sources or a confidence level, enable web search |
| Wrong tone | It defaults to helpful-professional | Describe the tone plainly, or give a sample |

- **The iteration mindset:** treat the first draft as a starting point. Give specific feedback: "cut the first two paragraphs and make the conclusion action-oriented" beats "make it shorter". Know when to start fresh.
- A recap of AI Fluency's 4Ds. The Lesson 2 framework is *Description*; troubleshooting draws on *Discernment* and *Diligence*.
- **Evals for your own work:**
  1. Gather 5–10 examples of a recurring task.
  2. Write prompts that would produce them.
  3. Compare Claude's output with your originals: key information, tone, what's missing.
  4. Refine the prompts, add examples, or decide where human review stays essential.
- Video (from *AI Fluency for nonprofits*): the **delegation–diligence loop**. Rio, a program director, tests Claude on *last quarter's* data, where he already knows the answer.
  - Claude found the attendance–employment correlation but missed the combined housing-and-jobs program.
  - A better description fixed it, and he noted that future prompts must ask about program type.
  - For cohort analysis, Claude would have had to *guess* enrollment dates, so those must be included in the data.
  - Lesson: **validation builds confidence but doesn't remove responsibility.** If several rounds of refinement can't match known results, don't delegate that task.

### Lesson 4: Claude desktop app: chat, Cowork, Code (Day 1)

There are three "shapes" of work. Recognizing which one you're in is the skill this lesson teaches.

| Shape | When | Where it lives |
|---|---|---|
| **Turn by turn** | The answer changes your next question; you want your judgment on every turn; it's quick | **Chat**: quick entry (double-tap Option on Mac), screenshots and window sharing, dictation, desktop connectors |
| **Hand it off** | Several steps in sequence; finished files saved where you need them; work that spans tools; runs on a schedule or in the background | **Cowork**: local folder access (reads and saves into your folder), scheduled tasks (run remotely, even while your computer sleeps), subagents, projects, browser use (Claude in Chrome), computer use (research preview), plugins |
| **Build software** | Reading, writing, testing and running code in a codebase | **Code tab**: Local (your folder, a dev server) or Cloud (a GitHub repo; keeps running when the app is closed). Modes: manually approve, accept edits, plan |

- Handing work off doesn't mean letting go. Claude asks scoping questions, shows its plan, lets you watch and steer, and asks before consequential actions like sending email or sharing files.
- With the "new Claude experience", Chat and Cowork merge: both kinds of work happen in any conversation.
- Reflection: which of this week's requests were really whole tasks that you fed in one question at a time? For a task to hand off, write down the *outcome* you want, not your first question.

## Module 2: Organizing your work and knowledge

### Lesson 5: Introduction to projects (Day 1)

- **Projects** are self-contained workspaces with their own chats, **knowledge** (uploaded files used in every chat) and **instructions** (tone, expertise, process, requirements that apply to every chat).
- Use one for ongoing work that has reference material you reuse, consistent requirements, or team collaboration.
- Setup:
  1. Create the project. Claude doesn't see the description; it's for you and your teammates.
  2. Write instructions: context, process, tone, requirements. They can automate workflows, e.g. "when I upload a transcript, summarize it with this template".
  3. Upload knowledge: PDF, DOCX, CSV, TXT, HTML, or Google Drive.
- **Scaling:** as knowledge approaches the context limit, the project switches to **RAG** and retrieves only the relevant parts, giving up to about 10x more capacity.
- **Chats inside a project don't share context with each other.** Only the knowledge base is shared. A file uploaded within one chat stays out of the knowledge base.
- **Team sharing** (Team and Enterprise) has three permission levels: *Can view* (read and chat), *Can edit* (change instructions, knowledge and members) and *Owner*. Shared projects appear under *Shared with me*.
- Best practices:
  - Start with a focused project.
  - Keep the knowledge current, because stale files lead to stale answers.
  - Write specific instructions.
  - Give files descriptive names ("Q4-2025-Sales-Report.pdf", not "report.pdf"), since Claude uses file names when retrieving.
  - Refer to documents by name in your questions.

### Lesson 6: Creating with artifacts (Day 1)

- **Artifacts** are outputs that stand on their own: docs, decks, designs, dashboards and prototypes. They open in a window beside the chat and are saved in the **Artifacts tab**, separate from the conversation. To make sure you get one, say "Create this as an artifact."
- There are three dedicated kinds, available on every plan (admins can turn them off on Team and Enterprise):
  - **Claude Design:** landing pages, mockups and prototypes on an editable canvas, and it can use your design system.
  - **Claude Slides:** decks. Edit, present, and export to PowerPoint, Google Slides or PDF.
  - **Claude Docs:** living documents you write together with Claude. Co-editing, comments, and charts from connected tools (a snapshot you refresh on request). Export to Google Docs or .docx.
  - You can convert between them, e.g. "turn this doc into a deck".
- Other artifacts (charts, flowcharts, interactive pages, working code) are available on every plan. Artifacts are different from **file creation**, which gives you a .docx, .xlsx, .pptx or .pdf to download.
- **Ways to change an artifact:** edit it directly, leave a comment for Claude on a specific element (or @-mention Claude in a doc), or ask in the chat.
- **Sharing:** artifacts are private until you share them. Designs, decks and docs can be shared per person with view, comment or edit rights. Pro and Max offer private or anyone-with-the-link. On Team and Enterprise, links stay inside the organization unless an Owner enables external sharing. Only the artifact is shared, never the conversation.
- Tips:
  - Ask for the *deliverable*, not just the content: "a one-page doc for leadership", not "summarize Q3".
  - Be specific about features.
  - Say who the end user is.
  - Make the artifact at the *end* of a conversation, once Claude understands the problem.
  - Refresh embedded data when you need it current.

### Lesson 7: Working with skills (Day 2)

- **Skills** are folders of instructions, scripts and resources that Claude loads when a task needs them. They work like expertise packages for repeatable work.
  - **Anthropic skills**, such as Excel, Word, PowerPoint and PDF creation, are used automatically.
  - **Custom skills** encode your own workflows: brand rules, meeting-note formats, analysis methods.
- **Enabling:** they need *Code execution and file creation* (in *Settings > Capabilities*). Manage them in *Customize > Skills*. On Team and Enterprise, an Owner controls the toggles.
- Claude picks skills automatically, and you'll see the skill named in its reasoning. Uploaded files are processed in a sandbox and returned as *new* versions, not edited in place. Network access may need to be allowed.
- **Security:** skills can contain executable code. Only install them from trusted sources, and read an external skill before using it. Custom skills stay private until you share them.
- **Making a custom skill:** describe what you want in a chat, answer Claude's interview questions, upload reference material, then use *Save skill*. You can also upload a skill file manually, and iterate by asking Claude to edit it.
- **Projects store knowledge, skills perform tasks.** A project provides the *what*; a skill provides the *how*. They combine well, e.g. a call-prep skill that reads customer profiles stored in a project.

## Module 3: Expanding Claude's reach

### Lesson 8: Connecting your tools (Day 2)

- **Connectors** let Claude search, read and, where permitted, act in the tools that hold your work, instead of you pasting copies. They're powered by **MCP**, the Model Context Protocol, "like USB-C for AI".
- There are two kinds:
  - **Web connectors** for cloud services: Gmail, Notion, Slack, Asana, Linear, Stripe, and others.
  - **Desktop extensions**, which run locally through the Desktop app and give access to files, the browser and native apps.
- The directory lists *connectors*, not apps. For example, Atlassian Rovo covers both Jira and Confluence. If a tool isn't listed, you can add a custom connector.
- Setting one up: find it in *Customize > Connectors* or under *+ > Connectors*, click Connect, sign in, review the permissions, then test it ("Can you access my …?"). On Team and Enterprise, an Owner must add the connector first.
- Exercise: toggling sources on and off shows that a connection only gives a request *somewhere to point*. You still have to ask for it.
- **Security:**
  - Access is scoped, and permissions can be toggled per tool.
  - **Claude sees only what you can see.**
  - Access can be revoked at any time.
  - Only install connectors from trusted sources.

### Lesson 9: Enterprise Search (Day 2; Team and Enterprise only)

- An "Ask {Org}" project in the sidebar works like a pre-built project for the whole company. It searches documents, chat and email (for example SharePoint, Slack, Gmail and Drive), combines the results, and **always cites its sources**.
- Uses: catching up ("what happened while I was out?"), policy and process questions, research, onboarding, and tracking projects.
- Setup has two steps. An Owner connects at least a Documents source and a Chat source (email is optional) and names the project. Then each user signs in to each service.
- It only shows what you already have permission to see. Conversations stay private, and connected data isn't indexed or stored separately.

### Lesson 10: Research mode for deep dives (Day 2; paid plans)

- **Research** is agentic. Claude plans with Thinking, then runs many searches that build on each other across the web and your connected tools, combines the results into a report, and **cites every claim**. It takes minutes, not seconds.
- Exercise: send each question to the right tool.
  - **Research:** multi-source comparisons, reports, competitor or vendor analysis.
  - **Web search:** a quick fact from one or two sources, when speed matters.
  - **Thinking:** deep reasoning without outside information, such as math, debugging or logic.
  - **Enterprise Search:** your organization's internal knowledge.
- To turn it on, use *+ > Research*. Web search must also be on; with the new Claude experience, Claude searches automatically.
- Prompt tips: give a specific goal, state the sections or structure you want, include constraints (budget, timeline, location), and have Claude help write the Research prompt first.
- With integrations, you can mix internal and external sources, e.g. "summarize Project X from my email and Slack, then research industry best practices".

## Module 4: Putting it all together

### Lesson 11: Claude in action: use cases by role (Day 2)

These are links into the **Use Case Gallery**:

| Role | Use cases |
|---|---|
| General | Project status reports, patterns in user feedback, brand guidelines packaged as a skill |
| Sales | Battle-card library, deal prep, sales reports |
| Marketing | Campaign performance analysis, adapting content across platforms |
| Finance | Financial models, investment memos, decoding an inherited spreadsheet |
| HR | Onboarding guides for new hires |
| Legal | Discovery timelines and pattern analysis |
| Research | Literature-review planning, checking statistics against raw data |

### Lesson 12: Other ways to work with Claude (Day 2)

The videos have no narration, so this is from the page text.

| Tool | Best for | Where it runs |
|---|---|---|
| Claude.ai | General tasks, research, writing, analysis, file creation | Web, desktop and mobile |
| **Claude Code** | Building features from plain English, debugging from pasted errors, learning an unfamiliar codebase, chores (lint fixes, merge conflicts, release notes) | Terminal, IDE, browser, Slack |
| Claude Cowork | Multi-step tasks: briefs, documents, organizing files, analysis | Desktop, plus web and mobile on some plans |
| Claude Tag | Drafting replies, summarizing threads, meeting prep, onboarding. Can start a Claude Code session from a bug report thread | Slack |
| Claude Design | Turning a brief, sketch or screenshot into a UI prototype; exploring variations; prototypes that follow your design system | Any conversation, plus claude.ai/design |
| Claude for Microsoft 365 | Excel (trace formulas and errors like #REF!, pivots), PowerPoint (draft and restructure decks within your template), Word (drafts and tracked changes), Outlook (triage and replies; in beta) | Office sidebars |
| Claude in Chrome | Summarizing pages, email, repetitive forms, testing website flows, internal tools without an API | Chrome sidebar. Paid plans only; best for low-risk tasks on trusted sites. It asks before risky actions, and some site categories are blocked |

## Module 5: Conclusion

### Lesson 13: What's next? (Day 2)

- A recap of every module, plus resources:
  - the AI Fluency and AI capabilities and limitations courses;
  - the Use Case Gallery, the Help Center and the prompting docs;
  - **Claude Code in Action** (Dojo Day 4) and Introduction to Claude Cowork;
  - the Connector Directory.
- Advice: start simple. Pick one recurring task this week, try it, iterate, and learn what works. Claude is a collaborator, not a replacement: "The rest comes from doing the work."

The course ends with a **quiz** and a **completion badge**. Neither is summarized here.

## One-line takeaways

1. Prompt like you're briefing a coworker: set the stage, define the task, specify the rules. Then iterate with specific feedback.
2. Name the shape of the work first: turn by turn in Chat, a hand-off in Cowork, or code in the Code tab.
3. Projects hold the *what* (knowledge and instructions), skills hold the *how* (repeatable procedures), and artifacts are the outputs you keep.
4. Connectors and Research bring in real data and citations, but Claude only sees what you can see, so install connectors and skills only from trusted sources.
5. Test Claude on a task where you already know the answer before you trust it with that task.

## How this applies to Dojo Tasks work

- **Lesson 3's eval loop is Day 2's job.** Before trusting Claude's analysis of the seeded tickets, compare it with what you can confirm by reproducing the bug.
- **Lesson 4's Code tab and permission modes** (manually approve, accept edits, plan) are Day 4's install-and-daily-loop work, and Claude Code in Action is Day 4's course.
- **Lessons 5 and 7, projects and skills:** a "Dojo Tasks" project with the README and plans as knowledge would work for chat-side questions. Lesson 7 is good preparation for Day 7 (skills and plugins).
- **Lesson 8's MCP connectors** lead into Day 8 (MCP, GitHub and Playwright).
