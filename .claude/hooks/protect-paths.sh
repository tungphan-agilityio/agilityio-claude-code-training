#!/usr/bin/env bash
# PreToolUse hook: block edits to guardrail and secret paths.
# Exit 2 blocks the tool call and shows stderr to Claude.
input=$(cat)
if command -v jq >/dev/null 2>&1; then
  path=$(printf '%s' "$input" | jq -r '.tool_input.file_path // .tool_input.notebook_path // empty')
else
  path=$(printf '%s' "$input" | grep -oE '"(file_path|notebook_path)"[[:space:]]*:[[:space:]]*"[^"]*"' | head -n1 | sed -E 's/.*"([^"]*)"$/\1/')
fi
[ -z "$path" ] && exit 0

case "$path" in
  */.github/workflows/*|.github/workflows/*|\
  */.claude/settings.json|.claude/settings.json|\
  */.claude/hooks/*|.claude/hooks/*|\
  */gradle/wrapper/*|gradle/wrapper/*|\
  */local.properties|local.properties|\
  *.jks|*.keystore)
    echo "Blocked by .claude/hooks/protect-paths.sh: $path is a protected path. Ask Nova to change it." >&2
    exit 2
    ;;
esac
exit 0
