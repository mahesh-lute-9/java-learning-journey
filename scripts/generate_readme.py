#!/usr/bin/env python3
"""
generate_readme.py

Regenerates the Progress Log, Key Concepts, References & Resources,
and Last Updated sections of README.md based on entries in progress.yml.

This script looks for HTML comment marker pairs in README.md and replaces
everything between them:

    <!-- PROGRESS_LOG_START -->     ... <!-- PROGRESS_LOG_END -->
    <!-- KEY_CONCEPTS_START -->     ... <!-- KEY_CONCEPTS_END -->
    <!-- RESOURCES_START -->        ... <!-- RESOURCES_END -->
    <!-- LAST_UPDATED_START -->     ... <!-- LAST_UPDATED_END -->

Run automatically by .github/workflows/update-readme.yml on every push to
main that touches progress.yml. Can also be run locally:

    pip install pyyaml
    python scripts/generate_readme.py
"""

import re
from datetime import datetime, timezone
from pathlib import Path

import yaml

ROOT = Path(__file__).resolve().parent.parent
README_PATH = ROOT / "README.md"
PROGRESS_PATH = ROOT / "progress.yml"


def load_entries():
    """Load and sort entries from progress.yml (oldest first)."""
    if not PROGRESS_PATH.exists():
        return []
    with open(PROGRESS_PATH, "r", encoding="utf-8") as f:
        data = yaml.safe_load(f) or []
    data.sort(key=lambda e: str(e.get("date", "")))
    return data


def build_progress_table(entries):
    if not entries:
        return "_No entries yet. Add your first day to `progress.yml`!_"

    lines = [
        "| Date | Day | Topic | Folder |",
        "|------|-----|-------|--------|",
    ]
    for e in entries:
        date = e.get("date", "")
        day = e.get("day", "")
        topic = e.get("topic", "")
        folder = e.get("folder", "")
        folder_cell = f"[{folder}]({folder})" if folder else "-"
        lines.append(f"| {date} | {day} | {topic} | {folder_cell} |")
    return "\n".join(lines)


def build_key_concepts_section(entries):
    blocks = []
    for e in entries:
        concepts = e.get("key_concepts") or []
        if not concepts:
            continue
        header = f"### Day {e.get('day', '')} — {e.get('topic', '')}"
        bullets = "\n".join(f"- {c}" for c in concepts)
        blocks.append(f"{header}\n{bullets}")

    if not blocks:
        return "_No key concepts yet. Add a `key_concepts` list to any entry in `progress.yml`._"
    return "\n\n".join(blocks)


def build_resources_section(entries):
    blocks = []
    for e in entries:
        resources = e.get("resources") or []
        if not resources:
            continue
        header = f"### Day {e.get('day', '')} — {e.get('topic', '')}"
        links = "\n".join(
            f"- [{r.get('title', r.get('url', 'Untitled'))}]({r.get('url', '#')})"
            for r in resources
        )
        blocks.append(f"{header}\n{links}")

    if not blocks:
        return "_No references yet. Add a `resources` list to any entry in `progress.yml`._"
    return "\n\n".join(blocks)


def replace_section(content, start_marker, end_marker, new_text):
    pattern = re.compile(
        re.escape(start_marker) + r".*?" + re.escape(end_marker),
        re.DOTALL,
    )
    if not pattern.search(content):
        raise ValueError(
            f"Markers '{start_marker}' / '{end_marker}' not found in README.md. "
            "Make sure they exist exactly as written."
        )
    replacement = f"{start_marker}\n{new_text}\n{end_marker}"
    return pattern.sub(lambda _: replacement, content)


def main():
    entries = load_entries()
    readme = README_PATH.read_text(encoding="utf-8")

    readme = replace_section(
        readme,
        "<!-- PROGRESS_LOG_START -->",
        "<!-- PROGRESS_LOG_END -->",
        build_progress_table(entries),
    )
    readme = replace_section(
        readme,
        "<!-- KEY_CONCEPTS_START -->",
        "<!-- KEY_CONCEPTS_END -->",
        build_key_concepts_section(entries),
    )
    readme = replace_section(
        readme,
        "<!-- RESOURCES_START -->",
        "<!-- RESOURCES_END -->",
        build_resources_section(entries),
    )

    last_updated = datetime.now(timezone.utc).strftime("%Y-%m-%d %H:%M UTC")
    readme = replace_section(
        readme,
        "<!-- LAST_UPDATED_START -->",
        "<!-- LAST_UPDATED_END -->",
        f"_Last updated: {last_updated} · {len(entries)} day(s) logged_",
    )

    README_PATH.write_text(readme, encoding="utf-8")
    print(f"README.md updated successfully with {len(entries)} entries.")


if __name__ == "__main__":
    main()
