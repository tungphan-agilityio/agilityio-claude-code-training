#!/usr/bin/env bash
# Stop hook: if Kotlin or Gradle files changed, make sure the app still compiles.
# Exit 2 keeps the session going and shows the errors to Claude.
input=$(cat)
# Do not loop: if this hook already blocked once, let the session stop.
if printf '%s' "$input" | grep -q '"stop_hook_active"[[:space:]]*:[[:space:]]*true'; then
  exit 0
fi

cd "${CLAUDE_PROJECT_DIR:-.}" || exit 0
changed=$(git status --porcelain -- 'android-app/*.kt' 'android-app/*.kts' 2>/dev/null)
[ -z "$changed" ] && exit 0

if ! out=$(cd android-app && ./gradlew :app:compileDebugKotlin -q 2>&1); then
  echo "compile-check: :app:compileDebugKotlin failed. Fix it before stopping." >&2
  printf '%s\n' "$out" | tail -n 40 >&2
  exit 2
fi
exit 0
