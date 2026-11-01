# AI Fluency: Framework and Foundations: lesson-by-lesson summary

- **Course:** https://academy.claude.com/courses/ai-fluency-framework-foundations (Anthropic Academy)
- **Taken for:** Day 1 of the Dojo training, done before Claude 101.
- **Instructors:** Rick Dakan (Ringling College of Art and Design), Joe Feller (Cork University Business School), Drew Bent and Maggie Vo (Anthropic).
- **Sources:** summaries of the video transcripts, scraped from each lesson's Transcript tab on 2026-10-31. Lessons 7, 11 and 14 have no video, so they're summarized from the lesson text. These are summaries, not transcripts.

## The framework at a glance

**AI fluency** means working with AI in ways that are **effective, efficient, ethical and safe**.

There are three ways to interact with AI:

| Mode | What AI does | Best when |
|---|---|---|
| Automation | Does a specific task you define | The outcome is clear |
| Augmentation | Acts as a thinking partner on a shared task | The solution isn't obvious and you need to explore |
| Agency | Acts independently on your behalf, within behavior you've set up | Routine or ongoing interactions (you act as "director", not "scriptwriter") |

There are four competencies, the **4Ds**, and each has three sub-skills:

| Competency | Question it answers | Sub-skills |
|---|---|---|
| **Delegation** | What should I do myself, and what should AI do? | Problem awareness, platform awareness, task delegation |
| **Description** | How do I communicate clearly with AI? | Product, process, performance description |
| **Discernment** | Is what AI gave me any good? | Product, process, performance discernment |
| **Diligence** | Am I using AI responsibly? | Creation, transparency, deployment diligence |

## Module 1: Introduction

### Lesson 1: Introduction to AI Fluency (video, 4 min)

The course is about how people work *with* AI, not about AI as a technology. The aim is to treat AI as a partner for creative problem-solving rather than a "next-level spell checker".

- Prompt tricks and tool tips go out of date quickly. A grounding framework lasts.
- The gap between what AI makes possible and what feels comfortable keeps widening. Organizations adopt AI without a strategy, and people get frustrated with systems they don't understand. The course aims to close that gap.
- The roadmap: the framework overview, the three interaction modes and the 4Ds, then a closer look at each D, with Anthropic staff covering how generative AI works, its capabilities and limits, and practical prompting.
- By the end you should have: a framework for your AI interactions, confidence about when and how to use AI, practical collaboration skills, and the ability to evaluate and take responsibility for AI-assisted outcomes.
- Exercise: create a free Claude account (or another LLM).

## Module 2: The AI Fluency Framework

### Lesson 2: Why do we need AI Fluency?

Having access to powerful AI doesn't mean you know how to use it well or responsibly. Think of an unexpected reply you didn't know how to handle, a request you couldn't articulate, or doubts about how your data is protected.

- Fluency isn't technical expertise or memorized prompts. It's a set of skills, knowledge, insights and values that reinforce each other and adapt as the technology changes.
- The three interaction modes:
  - **Automation:** AI executes a defined task (summarize a document, draft an email, plan an itinerary). It works when you know the outcome you want, and struggles when you don't.
  - **Augmentation:** AI as a creative and problem-solving partner (developing a character, a tough architecture problem, framing research). It helps you do your work better rather than doing it for you.
  - **Agency:** you set up AI's knowledge and behavior patterns and it acts on its own (email triage, a dynamic website experience, game characters).
- No mode is better than the others, and one project can use all three. Augmentation and agency make the most of what's unique about AI.
- AI can act as a tool, a medium, a partner or a co-creator. That gives people new roles, which need new skills: the 4Ds.

### Lesson 3: The 4D Framework

The four competencies apply whichever mode you're in.

- **Delegation:** the big picture. Understand your goal, know what AI can and can't do, and divide the work thoughtfully. Example: AI reviews long documents and discusses them with you, but you keep the critical analysis and conclusions.
- **Description:** detailed, context-rich communication. Compare "make me a logo" with a brief covering the brand's values, audience, colors and references. Or tell an AI tutor "don't give me the answer, walk me through it." Cover the goal, the output format, the approach, the context, and the tone of the interaction.
- **Discernment:** evaluate the output. Are the facts accurate? Does the reasoning hold up? Does it fit your brand and audience? Does it actually move you forward? This draws on your own expertise. Most work is small **description→discernment loops**.
- **Diligence:** responsibility. Check fairness and bias (for example when screening job applications), verify facts, protect sensitive data, be transparent about AI's involvement, and stand behind the result.
- The 4Ds aren't tied to any particular tool, so they stay relevant as the technology changes.

## Module 3: Deep Dive 1: What is generative AI?

### Lesson 4: Generative AI fundamentals (Drew Bent)

Generative AI *creates* new content, whereas traditional AI *classifies* existing data (writing an email versus flagging spam).

- **LLMs** are trained to predict and generate language. "Large" refers to billions of parameters, loosely like synapses.
- Three things came together to make them possible:
  1. Algorithm breakthroughs, especially the **transformer** (2017), which tracks relationships between words across long passages.
  2. A huge amount of digital data (websites, code, text).
  3. Compute: GPUs, TPUs and clusters.
- **Scaling laws:** more parameters, data and compute make performance improve predictably. New, unprogrammed abilities also **emerge**, such as step-by-step reasoning and adapting to a task from a few instructions.
- **Training:**
  - **Pre-training** predicts the next token over billions of examples, building a "map" of language and knowledge.
  - **Fine-tuning** teaches the model to follow instructions, be helpful and avoid harm, using human feedback and reinforcement learning toward being *helpful, honest and harmless*.
- **Use:** your prompt is text the model *continues*. It generates new text; it doesn't retrieve stored answers.
- **Context window:** the model's working memory, covering the prompts, responses and shared content. The model can't use anything outside it without tools such as web search.
- Three sources of its power: what it learned in training, **in-context learning** (adapting to a task from your prompt without retraining), and emergent capabilities from scale.

### Lesson 5: Capabilities & limitations

Getting to know AI is like getting to know a new colleague.

**Strengths:**
- Versatile language work: drafting in your voice, summarizing, translating, explaining any field.
- Switching tasks with no retraining.
- Keeping track of the conversation.
- Reaching beyond its own knowledge with tools: web search, files, other apps.

**Limitations:**
- **Knowledge cutoff:** it knows nothing after its training date, like someone back from a retreat with no internet, and needs tools to learn about recent events.
- **Hallucination:** training doesn't fact-check, so the model can confidently state plausible falsehoods. It generates text rather than retrieving it like a search engine.
- **Context window limit:** older content falls out, roughly first in, first out.
- **Non-deterministic:** the same question can get a different answer, which helps brainstorming but hurts when you need consistency. Some interfaces let you lower the randomness with a "temperature" setting.
- **Multi-step reasoning:** historically weak at maths and logic. Reasoning or "extended thinking" models are improving this.
- **Missing access:** it may not reach the data or tools a task needs, like a brilliant colleague without access to your internal database.

**Outlook:** techniques such as retrieval-augmented generation (RAG), tool use and better reasoning chip away at these limits, but some will remain. Humans bring judgment, creativity and ethical oversight; AI brings speed, scale, pattern recognition and volume. Keep experimenting as the balance shifts.

## Module 4: Delegation

### Lesson 6: A closer look at Delegation

Delegation mostly serves the **effective** and **efficient** goals.

- **Problem awareness:** good delegation starts with *your* expertise, not with AI. Before involving AI, know:
  - your goal, your vision, and what success looks like;
  - what kinds of work are involved: simple but time-consuming, uncertain (needs a thinking partner), unknown (needs data), or calling for critical judgment.

  "The most effective AI collaborators are experts in their fields first and AI delegators second."
- **Platform awareness:** know the different systems' strengths and weaknesses, such as speed versus depth and accuracy versus creativity. The landscape changes almost daily, so experiment hands-on.
- **Task delegation:** split the work. Decide what to automate, what to do together (augmentation), what stays human-only (critical judgment), and what agents can handle for you.
- Delegation isn't "handing over the wheel"; it's a deliberate split that plays to both sides' strengths.

### Lesson 7: Project planning and Delegation (exercise, no video)

1. Pick a medium-sized, multi-step project of about an hour's work that you'll carry through the rest of the course. Examples given: a presentation, an explainer post, a proposal, research on a trend, analysing a dataset, a short story outline, a learning plan, a tutorial.
2. Work with Claude on the vision. Ask it to question you until you're clear on what success looks like and why the project matters.
3. Together, break the project into tasks. For each task, decide what skills or capabilities it needs and whether it's human, AI or shared work. Keep the plan for Lesson 11.

## Module 5: Description

### Lesson 8: A closer look at Description

Description goes beyond writing clever prompts. It's about explaining, asking, giving context, steering a conversation that's drifting, and guiding the AI's reasoning. It builds a bridge between what you intend and what the AI can do. "AI can't read your mind": compare asking someone to make dinner with handing them a recipe.

- **Product description:** *what* you want. The context, the task, the format, the audience and the style. Set explicit requirements.
- **Process description:** *how* the AI should work. That can be general guidance (a manual), step-by-step instructions (a cookbook) or a demonstration ("here's how I do it"). Say which data to draw on, which issues to cover in what order, and what kind of analysis or workflow to use.
- **Performance description:** how the AI should *behave* with you. The lesson's headline point: **"AI tools are not databases or vending machines."** They're interactive systems whose behavior depends on context, so decide what kind of thinking partner you need:
  - converging on an answer or exploring options?
  - challenging your assumptions or following your lead?
  - detailed or concise?
  - explaining its reasoning or just answering?

## Module 6: Deep Dive 2: Effective prompting techniques

### Lesson 9: Effective prompting techniques

Prompting is description applied in practice. Think of briefing a willing new colleague who needs clear directions. It uses normal communication skills plus being explicit about what a person would infer, respecting the context window, and sometimes using machine-friendly formatting. Best practices change as models change, so experiment.

The six core techniques:

1. **Give context:** what you want, why, and *who you are*. "Tell me about climate change" becomes "3 impacts on tropical agriculture, last decade, for my interview at an Indonesian agricultural lab; I have an ecology degree but no climate background."
2. **Show examples** (few-shot or n-shot prompting). Try without examples first. Add them when a style is easier to show than describe, and cover a range of cases.
3. **Specify output constraints:** format, length, language, design details. The lesson's example is a portfolio site spec: its sections, a sticky responsive nav, the color palette, a dark/light toggle.
4. **Break complex tasks into steps** (chain of thought). Spell out the analysis steps. Do this especially when there are many valid ways to do the task, or when doing it well depends on your domain expertise.
5. **Ask it to think first.** Have it reason *before* answering, not justify afterwards. Reasoning models do this by default. As a bonus, you can see where it's going astray.
6. **Define a role, style or tone.** For example, "an experienced science teacher explaining to a bright 10-year-old", "a UX expert reviewing this wireframe", or even a specific person such as Feynman.

**"Secret weapon":** ask Claude to improve or write your prompt. ("I'm trying to get you to help me with X… help me craft an effective prompt.")

**Iterate.**
- Add specificity, examples or steps, or combine techniques.
- Ask for variations ("give me 3 versions"), a different format (an interactive artifact), or a confidence check ("how confident are you?").
- Sometimes a **fresh conversation** works better than repairing one that's gone off track.

**Works well:**
- Lead with a clear overview of the task.
- Include format specs and examples.
- Set explicit constraints.
- Give rich background.

**Mistakes:**
- Assuming it can read your mind.
- Cramming unrelated tasks into one prompt or conversation.
- Being vague about what success looks like.
- Not giving feedback on previous answers.

## Module 7: Discernment

### Lesson 10: A closer look at Discernment

Discernment is your quality control and the flip side of description. It needs domain expertise plus knowledge of how AI typically fails.

- **Product discernment:** is the output accurate, right for the audience and purpose, coherent, meeting the requirements, and actually adding value?
- **Process discernment:** how it got there. Watch for logical errors, lapses in attention, inappropriate steps, fixation on one interpretation, and circular reasoning. The lesson's example is the AI quietly bringing back ideas you already rejected. This matters most in complex tasks, where you can't easily check the answer.
- **Performance discernment:** how well it's interacting with you. Is it communicating in a useful way, responding to feedback, asking too many questions or being too brief?
- **Feedback** has four parts: state the problem, explain why it's a problem, suggest a concrete improvement, and revise your instructions or examples. Better description is usually the fix. Sometimes you need to **rethink delegation** instead, because it's the wrong tool or the wrong approach.

## Module 8: The Description-Discernment loop

### Lesson 11: The Description-Discernment loop (exercise, no video)

Carry out the Lesson 7 project with Claude:

1. Review the delegation plan.
2. Agree up front on product, process and performance expectations.
3. For each task, describe it (product, process, performance), discern the result (product, process, performance), then refine with feedback and repeat until you're happy.
4. Reflect on what worked and what you'd change.

The goal is a result better than either you or the AI would produce alone.

## Module 9: Diligence

### Lesson 12: A closer look at Diligence

Diligence covers the **ethical** and **safe** goals. Take responsibility for your AI use and make it rigorous, transparent and accountable. The analogy: a good driver cares about safety, the rules and other road users, not just getting there.

- **Creation diligence:** choose systems and ways of working deliberately.
  - How was the system built and trained?
  - Who owns the data you input, and who can see it?
  - Are you protecting privacy and security?
  - Does this fit your values and your organization's policy?

  For example, check the data policy and company permission before you paste sensitive information.
- **Transparency diligence:** be open with everyone who needs to know about AI's role (who, how, when, in how much detail). Expectations differ between personal, academic, creative and professional settings. This is about trust, not just rules. For example, tell colleagues which parts of a proposal were AI-assisted.
- **Deployment diligence:** you, not the AI, are responsible for what you share. Verify facts, check for bias and usage rights, and hold the work to the same standard as if you'd done it alone, like a journalist fact-checking an AI-assisted draft.
- Write personal guidelines, learn your organization's and industry's policies, and keep up as laws and regulation evolve.

## Module 10: Conclusion & badge

### Lesson 13: Conclusion (Rick Dakan)

A recap of the 4Ds with their sub-skills, which apply across automation, augmentation and agency. The key insights:

- **Delegation:** your expertise and judgment are the foundation. The best results come from iterating together so each side builds on the other.
- **Description:** thoughtful conversations full of context, examples and feedback often beat elaborate prompts.
- **Discernment:** critical evaluation is non-negotiable and guards against AI's limitations.
- **Diligence:** you stay accountable, and being transparent builds trust.

Fluency grows with practice; perfection isn't the goal. AI isn't a silver bullet and is only as useful and safe as we make it. "Be effective and efficient. Be ethical and safe."

### Lesson 14: Additional activities (no video)

Optional practice after the course:

- **Personal AI fluency plan:** rate yourself on each D (novice, developing or confident), note which modes you use most and least, pick one or two competencies to focus on, and plan practice activities with a timeline and success measures. Use Claude as a thinking partner.
- **Personal prompt and pattern library:** templates for 5–10 recurring tasks with placeholders, the description techniques and discernment checks that work for you, examples of good outputs, and a way to keep it up to date.
- **Game night (practice for description and discernment):**
  - Solve a crossword together, focusing on *steering* Claude's thinking ("it's only 5 letters", "maybe swallow the bird, not the verb").
  - Word association: find links between 12–20 random words, checking the answers (product), how it approached them (process) and how clearly it communicated (performance).
  - Twenty Questions, collaborative storytelling, a role-playing game with Claude as player or game master.
  - "Concepts & constraints", such as explaining a concept using only cooking metaphors.
  - Advanced puzzles. For programmers, Project Euler problems worked through with Claude.

The course ends with a **quiz** and a **completion badge**. Neither is summarized here.

## One-line takeaways

1. Know your problem before you open Claude (delegation starts with your own expertise).
2. Describe the product, the process and the performance, because AI can't read your mind.
3. Use the six techniques: context, examples, constraints, steps, think first, role. If you're stuck, ask Claude to write the prompt.
4. Judge the output, the process and the interaction, then feed back specifics. If description isn't the problem, rethink delegation.
5. You own the result: check data policies, disclose AI's role, verify before you ship.
