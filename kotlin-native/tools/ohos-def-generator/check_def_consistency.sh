#!/usr/bin/env bash
# Copyright (C) 2026 Eazytec. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

set -u

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
OUTPUT_DIR="$SCRIPT_DIR/output"
PLATFORM_DIR="$(cd "$SCRIPT_DIR/../../platformLibs/src/platform/ohos" && pwd)"

FIELDS=(
  "package"
  "headers"
  "headerFilter"
  "depends"
  "linkerOpts"
  "language"
  "compilerOpts"
  "enableUndefinedApiProtection"
)

get_field_value() {
  local file="$1"
  local field="$2"
  local started=0
  local value=""
  local line=""
  while IFS= read -r line || [ -n "$line" ]; do
    [[ "$line" =~ ^[[:space:]]*# ]] && continue
    if [ "$started" -eq 0 ]; then
      if [[ "$line" =~ ^[[:space:]]*$field[[:space:]]*=[[:space:]]*(.*)$ ]]; then
        local rhs="${BASH_REMATCH[1]}"
        started=1
        if [[ "$rhs" =~ \\$ ]]; then
          rhs="${rhs%\\}"
          value="$value${rhs} "
          continue
        else
          value="$value${rhs}"
          break
        fi
      fi
    else
      local cont="$line"
      if [[ "$cont" =~ \\$ ]]; then
        cont="${cont%\\}"
        value="$value${cont} "
      else
        value="$value${cont}"
        break
      fi
    fi
  done < "$file"
  value="$(printf "%s" "$value" | tr '\n' ' ' | sed -E 's/[[:space:]]+/ /g' | sed -E 's/^[[:space:]]+//; s/[[:space:]]+$//')"
  printf "%s" "$value"
}

normalize_value() {
  local val="$1"
  val="$(printf "%s" "$val" | tr '\n' ' ' | sed -E 's/[[:space:]]+/ /g' | sed -E 's/^[[:space:]]+//; s/[[:space:]]+$//')"
  if [ -z "$val" ]; then
    echo ""
    return
  fi
  printf "%s" "$val" | tr ' ' '\n' | sed '/^$/d' | sort -u | tr '\n' ' ' | sed -E 's/[[:space:]]+$//'
}

print_file_header() {
  local name="$1"
  :
}

mismatch_files=()
mismatch_details=()
checked_counter=0
processed_counter=0
total_files="$(find "$OUTPUT_DIR" -maxdepth 1 -type f -name '*.def' | wc -l | tr -d ' ')"
printf "\r检查进度: 0/%s" "$total_files" >&2

for def in "$OUTPUT_DIR"/*.def; do
  [ -e "$def" ] || continue
  ((checked_counter++))
  ((processed_counter++))
  if (( processed_counter % 10 == 0 || processed_counter == total_files )); then
    printf "\r检查进度: %d/%d" "$processed_counter" "$total_files" >&2
  fi
  base="$(basename "$def")"
  platform="$PLATFORM_DIR/$base"
  print_file_header "$base"
  fields_match=1
  mismatched_in_file=()
  if [ -f "$platform" ]; then
    for field in "${FIELDS[@]}"; do
      val_out="$(get_field_value "$def" "$field")"
      val_plat="$(get_field_value "$platform" "$field")"
      norm_out="$(normalize_value "$val_out")"
      norm_plat="$(normalize_value "$val_plat")"
      if [ "$norm_out" != "$norm_plat" ]; then
        fields_match=0
        mismatched_in_file+=("$field")
      fi
    done
    if [ "$fields_match" -eq 0 ]; then
      mismatch_files+=("$base")
      mismatch_details+=("$base: ${mismatched_in_file[*]}")
    fi
  else
    mismatch_files+=("$base")
    mismatch_details+=("$base: platform_missing")
    mismatch_files+=("$base")
  fi
done
printf "\r检查完成: %d/%d\n" "$processed_counter" "$total_files" >&2

echo "========================================"
echo "汇总:"
echo "  已检查文件数: $checked_counter"
echo "  字段不一致的文件数: ${#mismatch_files[@]}"
if [ "${#mismatch_files[@]}" -gt 0 ]; then
  echo "  字段不一致的文件列表:"
  for item in "${mismatch_details[@]}"; do
    echo "    $item"
  done
fi
echo "========================================"
