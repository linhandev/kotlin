#!/usr/bin/env bash
# Copyright (C) 2026 Eazytec. All rights reserved.
# Eazytec is pleased to support the open source community by making CPF-KMP-CMP available.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# OHOS .def generator: clear output then run the generator

set -e
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

# Load config if present
[ -f "$SCRIPT_DIR/def-generator.config" ] && . "$SCRIPT_DIR/def-generator.config"

OUTPUT_REL="${DEFAULT_OUTPUT_REL:-output}"

KONAN_DATA_DIR="${KONAN_DATA_DIR:-$HOME/.konan}"
KONAN_SYSROOT_OHOS="${KONAN_SYSROOT_OHOS:-sysroot-ohos-aarch64-6.0.2.640-02}"
KONAN_SYSROOT_HMS="${KONAN_SYSROOT_HMS:-sysroot-hms-aarch64-6.0.2.640-02}"

# If value is absolute path, use as-is; else under KONAN_DATA_DIR/dependencies/
resolve_sysroot() {
  local val="$1"
  case "$val" in
    /*) echo "$val" ;;
    *)  echo "$KONAN_DATA_DIR/dependencies/$val" ;;
  esac
}

SOURCE_OHOS=$(resolve_sysroot "$KONAN_SYSROOT_OHOS")
SOURCE_HMS=$(resolve_sysroot "$KONAN_SYSROOT_HMS")
if [ ! -d "$SOURCE_OHOS" ]; then
  echo "Error: Konan OHOS sysroot not found: $SOURCE_OHOS"
  exit 1
fi
if [ ! -d "$SOURCE_HMS" ]; then
  echo "Error: Konan HMS sysroot not found: $SOURCE_HMS"
  exit 1
fi
SOURCE_ARGS="--source $SOURCE_OHOS --source $SOURCE_HMS"

# Clear output directory (keep the directory itself)
if [ -d "$OUTPUT_REL" ]; then
  rm -rf "${OUTPUT_REL:?}"/*
  echo "Cleared: $OUTPUT_REL"
fi
mkdir -p "$OUTPUT_REL"

# Prefer gradle run: run from source without building jar, always use latest code and def-generator-rules.json
if command -v gradle >/dev/null 2>&1; then
  exec gradle run --args="$SOURCE_ARGS --output $OUTPUT_REL $*"
fi
if [ -x "$SCRIPT_DIR/gradlew" ]; then
  exec "$SCRIPT_DIR/gradlew" run --args="$SOURCE_ARGS --output $OUTPUT_REL $*"
fi

# When Gradle is not available use jar: build jar with repo gradlew then run
REPO_GRADLEW="$SCRIPT_DIR/../../../gradlew"
JAR=""
if [ -x "$REPO_GRADLEW" ]; then
  echo "Building jar with repo gradlew: ../../../gradlew -p . jar"
  "$REPO_GRADLEW" -p "$SCRIPT_DIR" jar
  JAR=$(find "$SCRIPT_DIR/build/libs" -name "ohos-def-generator*.jar" 2>/dev/null | head -1)
fi
if [ -n "$JAR" ] && [ -f "$JAR" ]; then
  exec java -jar "$JAR" --source "$SOURCE_OHOS" --source "$SOURCE_HMS" --output "$OUTPUT_REL" "$@"
fi

echo "Error: Cannot run. Please install Gradle or ensure an executable gradlew exists at repo root, then run this script."
exit 1
