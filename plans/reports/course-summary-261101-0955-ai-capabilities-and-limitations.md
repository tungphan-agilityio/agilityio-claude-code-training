# AI Capabilities and Limitations: lesson-by-lesson summary

- **Course:** https://academy.claude.com/courses/ai-capabilities-and-limitations (Anthropic Academy)
- **Taken for:** Day 1 of the Dojo training. It's the companion to AI Fluency: the 4Ds are what *you* do, and this course explains what *the model* does and why.
- **Instructors:** Kristen, Maggie, David and Matt, from Anthropic's education, safety and user research teams.
- **Sources:** summaries of the video transcripts, scraped from each lesson's Transcript tab on 2026-11-01. The four "Try it out" lessons (5, 7, 9 and 11) are interactive pages with no video, so they're summarized from the page text; their interactive parts (simulators, plots, the memory test) aren't captured. These are summaries, not transcripts.

## The course at a glance

**Two training stages** give a model its character:

| Stage | What it builds | What it leaves behind |
|---|---|---|
| Pre-training | A document completer: "given everything so far, guess what comes next", repeated billions of times | Breadth of knowledge, fluency, and the habit of continuing patterns |
| Fine-tuning | An assistant, trained on curated examples and human-preference signals | Helpfulness and instruction-following, plus four "shadow" traits: sycophancy, verbosity, overcaution, loosely calibrated confidence |

**Four properties** of generative AI. Each is a continuum running from a capability zone to a limitation zone:

| Property | Question | Capability zone | Limitation zone | Product features that help |
|---|---|---|---|---|
| **Next-token prediction** | Where do answers come from? | Well-worn tasks: summarize, reformat, explain common concepts, draft in familiar styles | Thin paths: niche topics, specifics. Fluent fabrication and misplaced confidence | Citations and source grounding, uncertainty signals, constrained generation and skills, generator-verifier loops |
| **Knowledge** | What does the model know? | Mainstream, well-documented, stable, pre-cutoff topics | Post-cutoff, stale, niche, local or less-represented topics; inherited bias; source amnesia | Web search, MCP connectors, tools, cutoff disclosure |
| **Working memory** | What is it paying attention to? | Material that fits comfortably in the context window, within one session | Overflow drops material silently; lost in the middle; empty at the start of each new session. A *cliff*, not a slope | Memory, compaction, projects, skills, multi-agent workflows, bigger windows |
| **Steerability** | How much are you in control? | Short, concrete, checkable instructions: format, tone, persona, length | Abstract goals, long reasoning chains, letter over spirit, prompt injection | System prompts and custom instructions, visible reasoning, structured outputs |

**Most failures are two properties colliding.** Name the pair, and the fix follows.

## Module 1: Foundations

### Lesson 1: Intro to AI Capabilities and Limitations (video)

- The 4Ds are human competencies. This course covers the machine side: where outputs come from. You can't delegate well without knowing where the model is strong, or discern well without knowing how an output was produced.
- The course focuses on how AI is *created*, so it stays relevant as models and products change. Context windows grow and hallucination rates drop, but the *shape* of each property stays the same.
- Roadmap: the two training stages, then the four properties, then how they interact.
- Do the exercises on your own real work, in a domain where you're the expert and can tell when something is off. The goal is "a calibration you can feel, not a list of terms you memorize."

### Lesson 2: What We Mean by AI (video)

- Much of AI is not generative: recommendation engines, spam filters, fraud models and call routing all sort, rank, classify or predict. This course is about **generative AI**, which produces new content.
- Generative AI is a prediction system. It's strong and weak along specific, predictable axes, and **the strength and the weakness usually come from the same property**. It writes compellingly because it predicts, and it hallucinates for the same reason.
- A quick preview of the four properties.
- The goal is **calibrated trust**: neither trusting it wholesale nor withholding trust wholesale. For each property, ask where your task sits, whether it's well-trodden or near an edge, and what's at stake if it's wrong.

### Lesson 3: How AI Gets Its Character (video)

- Helpfulness is built deliberately, in layers.
- A model with only pre-training doesn't answer "What is the capital of France?" It *continues the document*, for example with "Paris. What's the capital of Germany? Berlin…", because it has seen quizzes like that.
- Fine-tuning turns the completer into an assistant. Because it relies on human judgments of what good looks like, it leaves four **shadow traits**:
  1. **Sycophancy:** it validates readily and backs down under light pushback, even when it was right.
  2. **Verbosity:** it defaults to long answers because thoroughness scored well in training.
  3. **Overcaution:** it hedges or refuses requests that are actually safe.
  4. **Loose confidence calibration:** its stated confidence is only loosely tied to how reliable it actually is.
- These traits show up in all models, to different degrees. Anthropic publishes Claude's constitution, which describes how Claude's character is shaped.
- In practice: if it caves when you push back, factor in sycophancy. If you get essays when you wanted bullets, that's the verbosity default. Heavy caveats on a harmless question are overcaution.

## Module 2: The four properties

### Lesson 4: Next Token Prediction (video)

- Generation is composing, not looking things up. It's "closer to an extraordinarily sophisticated autocomplete than to a search engine". A citation can *look* real and still satisfy the pattern.
- Demo: Claude summarizes a well-known essay cleanly, which is a well-worn path. Asked for three papers by a mid-level researcher in a niche subfield, it answers with the same confident tone, but some papers may be invented.
- **Strengths:** fluent text in any register, fast synthesis across fields, and coherent continuation of any thread you give it. **Failures:** hallucination, inconsistency and misplaced confidence.
- Habits:
  - **A confident tone is not a signal of accuracy.** Smoothness and correctness are independent.
  - **Fabrication concentrates in specifics:** names, dates, statistics, citations, quotes and URLs. The more precise a claim, the more it needs checking.
  - Treat outputs as drafts to verify, especially when stakes are high or the domain is unfamiliar to you. Use citations or source grounding when the tool offers them.
- 4D link: this is the core of **Discernment**, and it informs **Delegation**, since capability-zone tasks are safer to hand off.

### Lesson 5: Try It Out: Next Token Prediction (interactive)

- A "Text Your Friend Markov" simulator, a phone-style next-word game. You "train" it by tallying word transitions from five messages into a **frequency table**. Normalizing each row gives a probability distribution, and picking the next word from it is **sampling**.
- Scaling up the data grows the table. The "knobs" are the sampling strategies developers pass to Claude: always take the top word, sample by probability, boost the likely choices (temperature), cut below a threshold (top-p), or only consider the top N (top-k).
- **The bridge to LLMs:**
  - A Markov chain reads only the last word, while an LLM reads the whole conversation.
  - A Markov chain computes the distribution with a table lookup, while an LLM uses a forward pass through billions of parameters.
  - The sampling step is the same.
  - Training is radically different: LLMs give up the table's explainability in exchange for far more context and capability.
- History: Markov published the idea in 1906. N-gram models powered phone keyboards by about 2010. Neural networks took over around 2015, and transformers arrived in 2017.

### Lesson 6: Knowledge (video)

- The model knows things only through training on text. It has no experiences, doesn't browse unless it's given a search tool, and has a **knowledge cutoff**.
- Demo: "How does photosynthesis work?" gets a deep, accurate answer. "Who's the current mayor of Toledo?" might return a name that was right two years ago, and the model can't tell the difference.
- The question to ask isn't "does it know this?" but **"how well-represented was this in what it read?"**
- **Strengths:** very broad general knowledge, depth in well-covered domains, and connections across fields. Embeddings place related concepts near each other in a high-dimensional space.
- **Limitations:**
  - the cutoff, and **staleness** (facts that have since changed);
  - **uneven coverage**: rare topics, minority languages and niche domains suffer;
  - **inherited bias** in its sense of what's "normal";
  - **source amnesia**: it usually can't say where a fact came from.
- Workarounds: web search, MCP connectors (your wiki or a database), tools, and cutoff disclosure.
- Habits: verify anything time-sensitive. Test before you trust it in a new domain, because brilliance doesn't transfer to the domain next door. Watch for default assumptions, and use search or retrieval when they're available.
- 4D link: in **Delegation**, decide whether to bring the knowledge yourself via context, documents or search. In **Discernment**, recent, rare or local claims need a second look.

### Lesson 7: Try It Out: Knowledge (interactive)

- **Strings vs. meaning:** keyword search for "car" misses "automobile" and "my Civic needs new brakes". Search engines used to patch this by hand with synonym lists and stemming. Embeddings make meaning a *place* instead, and that mapping emerges from training.
- Exercise: plot three sources on two axes ("dinosaurs" and "roller coasters"), then plot the question "best dinosaur-themed roller coaster?" and retrieve the nearest *k*. That's **similarity search**.
- Adding axes adds coordinates. Real embedding models use about 1,024 dimensions. You can't picture them, but the math still works.
- The axes are **unlabeled and emergent**. Nobody chooses them, and you can't point at "dimension 847" and call it the dinosaur axis.
- An embedding model turns any string into a fixed-length vector, whether the input is three words or three paragraphs. Similarity is usually measured with **cosine similarity**, which compares vector *direction*: -1 is opposite, 0 unrelated, 1 identical. The encyclopedia lands in the middle of everything, "a jack of all trades".

### Lesson 8: Working Memory (video)

- Everything the model uses (your instructions, its own replies, uploaded documents, the whole conversation) lives in one fixed-size **context window**. When it overflows, the oldest material usually drops off, **silently**.
- The window **empties between sessions**, unless a feature such as memory or a `CLAUDE.md` file carries something forward.
- Unlike the other three properties, working memory **has a cliff**. It works right up until it doesn't, and you won't always get a warning.
- Inside the window, context is real leverage. Given a short style guide, the model adapts on the spot, with no retraining.
- The window has a ceiling because the model reads the whole context on every response. Attention isn't uniform even within that ceiling: material in the middle of a long input is **lost in the middle**.
- Product features that help: memory, compaction or summarization, projects and workspaces, skills (instructions loaded only when needed), multi-agent workflows with separate windows, and larger windows.
- Warning signs: quality slipping in a long chat, details from the middle of a document going missing, and expecting it to recall a previous session when memory is off.
- Habits:
  - Put the important material at the top.
  - Work through long documents in chunks or passes.
  - Use projects and skills.
  - When quality degrades, **start fresh with a short summary**. That often beats pushing on.
- 4D link: working memory is the mechanism that makes **Description** work, because everything you describe has to be inside the window.

### Lesson 9: Try It Out: Working Memory (interactive)

- A memory test (15 words, then recall them) demonstrates the **serial position effect**: primacy and recency are strong, and the middle is weak. That gives a U-shaped curve.
- LLMs show the same pattern. A 2023 Stanford study found accuracy dropped by more than 30% when the key fact was buried in the middle of a long context. It's structural, because transformer attention weights the edges of the context more heavily.
- A key instruction buried mid-conversation is risky. The safer pattern is to state it up front and **repeat it at the end**.
- **More context ≠ better results.** Every addition pushes other material toward the middle. "Curate ruthlessly, place strategically, and repeat what matters."

### Lesson 10: Steerability (video)

- Steerability is the model's ability to follow directions such as a table format, a skeptic's viewpoint, a role, tone or word limit. It comes from fine-tuning. A raw pre-trained model has no notion of helping.
- **Steerability isn't understanding.** Instructions pass through the same pattern-completion engine, so there's always a gap between the words you typed and what you meant.
- Demo: "Summarize in under 100 words and make it punchy." The instruction was followed exactly, but the one qualified finding that was needed got cut because it wasn't punchy. The model followed what was said and missed what was meant.
- The spectrum runs from short, concrete, checkable instructions ("as a table", "under 100 words", "this schema"), which sit in the capability zone, to abstract ones ("be insightful") and long reasoning chains, which sit at the edge.
- Failures:
  - **Reasoning drift:** a small error in step 2 carries through steps 3–5.
  - **Letter over spirit.**
  - **Prompt injection:** because the model follows instructions found in text, a malicious instruction in a document or web page can be followed too.
- Product features that help: system prompts and custom instructions (these don't dilute as the conversation grows), visible reasoning (catch drift at step 2), and structured output (JSON schemas, function calling).
- Habits:
  - **State the goal alongside the steps.**
  - Add checkpoints to long chains.
  - When something lands literally but uselessly, **restate the goal, not the instruction**. Saying "be concise" louder doesn't fix an intent problem.
  - Keep concrete, checkable instructions near the task.
- 4D link: **Description** both uses steerability and is limited by it. It shapes **Delegation** too: long chains and numeric precision need checkpoints or a different tool.

### Lesson 11: Try It Out: Steerability (interactive)

- "Letter vs. spirit": you step through workplace editing requests ("make it shorter", "make it more professional", "add more detail", "summary"). For each, you see Claude's literal result, the underlying goal it missed, and a better prompt that states that goal.
- Example: a request to "make it shorter" for a roadmap follow-up email. The email recommends platform consolidation, flags a trade-off (mobile redesign pushed to Q3) and asks for sign-off by Friday. Only the first scenario's setup was captured, not the reveals.
- Lesson: unstated intent gets lost, so say what the edit is *for*.

## Module 3: Putting it together

### Lesson 12: When Properties Collide (video)

- Most surprises in practice are **two properties meeting**:
  - **A fabricated citation = next-token prediction + a knowledge gap.** The model generates citation-shaped text and doesn't know that a gap lies underneath. Fix: verify specifics independently, or use source grounding so it retrieves real documents.
  - **Constraints ignored 20 messages later = working memory + steerability.** Early context fades, and the most salient recent instructions take over. Fix: supply the critical context again, or start fresh with the essentials up front.
- Before you start, ask which properties your task involves. Diagnose before you fix: a knowledge problem and a working-memory problem can look alike but need different fixes. Jumping straight to "how do I fix my prompt?" is guessing.
- This is **Discernment** in action ("I need to ground this in a source again", "I need to invite pushback"). Recurring compound failures feed back into **Delegation**: restructure those tasks, split them up, or keep them yourself.

### Lesson 13: Next Steps (video)

- Recap: two training stages, four properties, and failures as collisions between properties. Ask "which two things collided?", not "what broke?"
- How the two courses fit together: **the 4Ds are what you do; the four properties are what you're responding to.**
  - Next-token prediction sharpens **Discernment**, because fluency and accuracy are independent.
  - Working memory sharpens **Description**, because context is leverage and the model doesn't remember everything.
  - Steerability sharpens **Delegation**, because you know where your control is precise and where it isn't.
- **Calibrated trust is a habit, not an attitude.** Before handing off a task, check:
  - Is this well-worn or sparse territory?
  - Is the topic recent or stable?
  - Does it fit comfortably in the window?
  - Are my instructions concrete, or is there a gap between my words and my intent?

  Then adjust: verify more where fabrication concentrates, add context where the model would have to guess, and add checkpoints to long chains.
- Next: practice on real work, notice where your predictions about AI behavior are right and wrong, revisit the 4Ds through these lenses, and keep testing the edges as they move.

The course ends with a **quiz** and a **completion badge**. Neither is summarized here.

## One-line takeaways

1. The model is a predictor: fluency and accuracy are independent, and fabrication concentrates in specifics such as names, dates, numbers, citations and URLs.
2. Its knowledge is broad but frozen and uneven. Verify anything recent, niche or local, and use search or connectors to fill gaps.
3. The context window is finite and has a cliff. Put key instructions first and repeat them at the end, and start a fresh chat with a summary when quality slips.
4. Instructions are followed to the letter, not the spirit. State the goal, not just the format.
5. When output goes wrong, name the two properties that collided, then pick the fix.
6. Watch for fine-tuning's shadow traits: sycophancy when you push back, verbosity, overcaution and overconfidence.

## How this applies to Dojo Tasks work

- **Day 1 exercise, Question 1:** the v1 bug summary added a severity rating and cause rankings. That's next-token prediction filling a knowledge gap with plausible specifics, and "don't invent facts; mark assumptions" was the fix.
- **Day 1 exercise, Question 2:** the `TextFieldState` version note was simplified. Version numbers are the kind of specific that needs a source check.
- **Day 5 onward (`CLAUDE.md`, `/compact`, fresh sessions):** these are working-memory features. Keep `CLAUDE.md` short, with the key rules near the top.
