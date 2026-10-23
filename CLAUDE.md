# CLAUDE.md

Project instructions live in `AGENTS.md` (the tool-neutral format) and are imported here:

@AGENTS.md

## Claude Code specifics

- Skills in `.claude/skills/`: `ktelegram` (procedures for adding Telegram types/methods and releasing)
  and `ktelegram-usage` (how consumers use the library). Prefer invoking them over re-deriving the procedure.
- Local permission overrides belong in `.claude/settings.local.json` (gitignored), not in `AGENTS.md`.
