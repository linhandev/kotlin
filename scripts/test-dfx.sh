#!/bin/sh
# Eazytec is pleased to support the open source community by making CPF-KMP-CMP available.
# Copyright (C) 2026 Eazytec. All rights reserved.

# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at

# http://www.apache.org/licenses/LICENSE-2.0

# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# DFX test script: run Ohos DFX black-box tests (HiDebug / fatal_message / backtrace, etc.).
# POSIX sh compatible (run with sh or zsh).

set -e

# ========== Paths and global config ==========
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
ROOT_DIR=$(cd "$SCRIPT_DIR/../" && pwd)
cd "$ROOT_DIR"

DEPLOY_VERSION=${DEPLOY_VERSION:-2.2.21-OH-001}
TEST_TARGET=${TEST_TARGET:-ohos_arm64}
DFX_TEST_DATA_DIR="$ROOT_DIR/native/native.tests/testData/dfx"
DEFAULT_TEST_NAME="ReportBacktraceToOhosLogTest"
BREAKPAD_GIT_REPO=${BREAKPAD_GIT_REPO:-https://gitee.com/mirrors/breakpad.git}
BREAKPAD_GIT_REVISION=${BREAKPAD_GIT_REVISION:-v2024.02.16}

GRADLE_VERSION_OPTS=""
if [ -n "$DEPLOY_VERSION" ]; then
  GRADLE_VERSION_OPTS="-PdeployVersion=$DEPLOY_VERSION -Pversions.kotlin-native=$DEPLOY_VERSION -PkonanVersion=$DEPLOY_VERSION -Pbootstrap.kotlin.version=$DEPLOY_VERSION -Pbootstrap.local.version=$DEPLOY_VERSION"
fi

# Helper: is $1 in space-separated list $2
_is_in_list() { case " $2 " in *" $1 "*) return 0;; *) return 1;; esac; }

_count_words() { set -- $1; echo $#; }

# ========== Minimum API level per test class ==========
# get_min_api TEST_NAME -> N (default 17). Extend here when adding tests that require a higher API.
get_min_api() {
  case "$1" in
    ReportBacktraceToOhosLogTest) echo 17 ;;  # HiDebug_SetCrashObj on API <23: tolerated via try-catch in tests
    OomMemDumpHiAppEventTest) echo 26 ;;      # ReportFrameworkMemAnomaly / RegisterMemDumpListener @ API 26
    *) echo 17 ;;
  esac
}

# ========== Usage ==========
#   ./scripts/test-dfx.sh [options] [testName|all]
#   - No options: run default test ($DEFAULT_TEST_NAME), use build cache
#   -c/--compile: clean test cache, force recompile, then run
#   -r: only regenerate test sources (after adding new .kt under testData/dfx)
#   -v all | -v XxxTest: emulator mode (must be listed in EMULATOR_SUPPORTED_TESTS)
#   -api<N>: skip tests whose get_min_api > N (N >= 17)
# Examples:
#   ./scripts/test-dfx.sh
#   ./scripts/test-dfx.sh all
#   ./scripts/test-dfx.sh ReportBacktraceToOhosLogTest
#   ./scripts/test-dfx.sh -c ReportBacktraceToOhosLogTest
#   ./scripts/test-dfx.sh -r
#   ./scripts/test-dfx.sh -api23 all

# DFX tests allowed on emulator (-v); append new class names when adding cases
EMULATOR_SUPPORTED_TESTS="ReportBacktraceToOhosLogTest OomMemDumpHiAppEventTest"

# ========== Regenerate test sources ==========
if [ "$1" = "--regenerate" ] || [ "$1" = "-r" ] || [ "$1" = "regenerate" ]; then
    echo ""
    echo "🔄 Regenerating test files (OhosDFXTestGenerated.java)..."
    echo "   Scans: native/native.tests/testData/dfx"
    echo ""

    if [ -z "$JDK_18" ] && [ "$(uname -s)" = "Darwin" ]; then
      export JDK_18=$(/usr/libexec/java_home -v 1.8 2>/dev/null)
    fi
    if [ -z "$JDK_18" ]; then
      echo "❌ Error: JDK 1.8 is required. export JDK_18=<path_to_jdk_8>"
      exit 1
    fi

    GRADLE_REGEN_OPTS=""
    [ -n "$DEPLOY_VERSION" ] && GRADLE_REGEN_OPTS="-PdeployVersion=$DEPLOY_VERSION -Pversions.kotlin-native=$DEPLOY_VERSION -PkonanVersion=$DEPLOY_VERSION -Pbootstrap.kotlin.version=$DEPLOY_VERSION -Pbootstrap.local.version=$DEPLOY_VERSION"
    ./gradlew $GRADLE_REGEN_OPTS -Pkotlin.native.enabled=true -Pbootstrap.local=true --dependency-verification=off \
        :native:native.tests:generateTests
    echo ""
    echo "✅ Test files regenerated."
    echo "📋 Usage: ./scripts/test-dfx.sh [ -c ] [ all | TestName ] or -r"
    exit 0
fi

# ========== Parse arguments ==========
SKIP_COMPILE=true
FORCE_COMPILE=false
TEST_INPUT=""
EMULATOR_MODE=false
TARGET_API=""

for arg in "$@"; do
    case $arg in
        --compile|-c)
            FORCE_COMPILE=true
            SKIP_COMPILE=false
            ;;
        --run-only|-o|--skip-compile|-s)
            SKIP_COMPILE=true
            FORCE_COMPILE=false
            ;;
        -v)
            EMULATOR_MODE=true
            ;;
        -api*)
            TARGET_API="${arg#-api}"
            case "$TARGET_API" in
              [0-9]*) [ "$TARGET_API" -lt 17 ] 2>/dev/null && { echo "❌ Error: -api must be >= 17."; exit 1; } ;;
              *) echo "❌ Error: -api must be followed by a number (e.g. -api23)."; exit 1 ;;
            esac
            ;;
        *)
            ;;
    esac
done

TEST_INPUT=""
for arg in "$@"; do
    case $arg in
        -api*) ;;
        *) TEST_INPUT="$arg" ;;
    esac
done

# ========== Discover tests from testData/dfx ==========
ALL_DFX_TESTS=""
if [ -d "$DFX_TEST_DATA_DIR" ]; then
  for f in "$DFX_TEST_DATA_DIR"/*.kt; do
    [ -f "$f" ] || continue
    name=$(basename "$f" .kt)
    ALL_DFX_TESTS="$ALL_DFX_TESTS $name"
  done
fi

# ========== Test scope ==========
if [ -n "$TEST_INPUT" ]; then
    if [ "$TEST_INPUT" = "all" ] || [ "$TEST_INPUT" = "--all" ] || [ "$TEST_INPUT" = "-a" ]; then
        TEST_FILTER="*OhosDFXTestGenerated*"
        FORCE_STANDALONE=""
        echo "========================================"
        echo "🧪 DFX Test Config (All Tests Mode)"
        echo "ROOT_DIR       = $ROOT_DIR"
        echo "DEPLOY_VERSION = ${DEPLOY_VERSION:-(not set)}"
        echo "TEST_TARGET    = $TEST_TARGET"
        echo "TEST_FILTER    = $TEST_FILTER"
        [ -n "$TARGET_API" ] && echo "TARGET_API     = $TARGET_API"
        echo "ARCH           = $(uname -m)"
        echo "========================================"
    else
        TEST_FILE_NAME="$TEST_INPUT"
        TEST_FILTER="*test${TEST_FILE_NAME}*"
        FORCE_STANDALONE="-Pkotlin.internal.native.test.forceStandalone=true"
        echo "========================================"
        echo "🧪 DFX Test Config (Single File Mode)"
        echo "ROOT_DIR       = $ROOT_DIR"
        echo "DEPLOY_VERSION = ${DEPLOY_VERSION:-(not set)}"
        echo "TEST_TARGET    = $TEST_TARGET"
        echo "TEST_FILE      = $TEST_FILE_NAME"
        echo "TEST_FILTER    = $TEST_FILTER"
        echo "STANDALONE     = true"
        [ -n "$TARGET_API" ] && echo "TARGET_API     = $TARGET_API"
        echo "ARCH           = $(uname -m)"
        echo "========================================"
    fi
else
    TEST_FILTER="*test${DEFAULT_TEST_NAME}*"
    FORCE_STANDALONE="-Pkotlin.internal.native.test.forceStandalone=true"
    echo "========================================"
    echo "🧪 DFX Test Config (default: $DEFAULT_TEST_NAME)"
    echo "ROOT_DIR       = $ROOT_DIR"
    echo "DEPLOY_VERSION = ${DEPLOY_VERSION:-(not set)}"
    echo "TEST_TARGET    = $TEST_TARGET"
    echo "TEST_FILTER    = $TEST_FILTER"
    echo "ARCH           = $(uname -m)"
    echo "========================================"
fi

# ========== Build --tests list for "all" ==========
GRADLE_TESTS_OPTS_STR=""
RUN_ALL=1
[ "$TEST_INPUT" = "all" ] || [ "$TEST_INPUT" = "--all" ] || [ "$TEST_INPUT" = "-a" ] && RUN_ALL=0

if [ "$RUN_ALL" = 0 ]; then
  EFFECTIVE_LIST="$ALL_DFX_TESTS"
  if [ "$EMULATOR_MODE" = true ]; then
    _intersect=""
    for t in $EFFECTIVE_LIST; do
      _is_in_list "$t" "$EMULATOR_SUPPORTED_TESTS" && _intersect="$_intersect $t"
    done
    EFFECTIVE_LIST="$_intersect"
  fi
  if [ -n "$TARGET_API" ]; then
    _filtered=""
    for t in $EFFECTIVE_LIST; do
      min=$(get_min_api "$t")
      [ "$min" -le "$TARGET_API" ] 2>/dev/null && _filtered="$_filtered $t"
    done
    EFFECTIVE_LIST="$_filtered"
  fi
  for t in $EFFECTIVE_LIST; do
    GRADLE_TESTS_OPTS_STR="$GRADLE_TESTS_OPTS_STR --tests '*test${t}*'"
  done
  _eff_count=$(_count_words "$EFFECTIVE_LIST")
  if [ "$_eff_count" -eq 0 ]; then
    echo "❌ No DFX tests to run (EMULATOR_MODE=$EMULATOR_MODE${TARGET_API:+, -api$TARGET_API})."
    exit 1
  fi
  if [ "$EMULATOR_MODE" = true ]; then
    echo "========================================"
    echo "📱 Emulator mode: $_eff_count test(s)"
    echo "========================================"
  fi
else
  if [ -n "$TEST_INPUT" ] && [ "$EMULATOR_MODE" = true ]; then
    _is_in_list "$TEST_INPUT" "$EMULATOR_SUPPORTED_TESTS" || {
      echo "❌ Test '$TEST_INPUT' is not in EMULATOR_SUPPORTED_TESTS."
      echo "   Supported: $EMULATOR_SUPPORTED_TESTS"
      exit 1
    }
  fi
  if [ -n "$TEST_INPUT" ] && [ -n "$TARGET_API" ]; then
    min=$(get_min_api "$TEST_INPUT")
    if [ "$min" -gt "$TARGET_API" ] 2>/dev/null; then
      echo "❌ Test '$TEST_INPUT' requires API $min, but -api$TARGET_API was specified."
      exit 1
    fi
  fi
  GRADLE_TESTS_OPTS_STR="--tests '$TEST_FILTER'"
fi

# ========== JDK 1.8 ==========
if [ -z "$JDK_18" ] && [ "$(uname -s)" = "Darwin" ]; then
  export JDK_18=$(/usr/libexec/java_home -v 1.8 2>/dev/null)
fi
if [ -z "$JDK_18" ]; then
  echo "❌ Error: JDK 1.8 is required. export JDK_18=<path_to_jdk_8>"
  exit 1
fi

# ========== HDC ==========
if [ -z "$HDC_PATH" ]; then
  for hdc_path in "$HOME/Library/OpenHarmony/Sdk/20/toolchains/hdc" "$HOME/Library/Huawei/Sdk/toolchains/hdc" \
      "/Users/$USER/Library/OpenHarmony/Sdk/20/toolchains/hdc" "/Users/$USER/Library/Huawei/Sdk/toolchains/hdc"; do
    if [ -f "$hdc_path" ] && [ -x "$hdc_path" ]; then
      export HDC_PATH="$hdc_path"
      echo "🔍 Found hdc at: $HDC_PATH"
      break
    fi
  done
  if [ -z "$HDC_PATH" ] && [ -n "$HARMONY_HDC" ]; then
    if [ -f "$HARMONY_HDC/hdc" ] && [ -x "$HARMONY_HDC/hdc" ]; then
      export HDC_PATH="$HARMONY_HDC/hdc"
    elif [ -f "$HARMONY_HDC/toolchains/hdc" ] && [ -x "$HARMONY_HDC/toolchains/hdc" ]; then
      export HDC_PATH="$HARMONY_HDC/toolchains/hdc"
    fi
    [ -n "$HDC_PATH" ] && echo "🔍 Found hdc from HARMONY_HDC: $HDC_PATH"
  fi
fi
if [ -n "$HDC_PATH" ]; then
  export HDC_PATH
  echo "✅ Using HDC_PATH: $HDC_PATH"
else
  echo "⚠️  HDC_PATH not set. OhosExecutor will try to find hdc automatically."
fi

# ========== Gradle ==========
DFX_EXCLUDE_NAPI_OPTS="-Pkotlin.native.runtime.excludeNapi=true"
GRADLE_NATIVE() {
  env JDK_18="$JDK_18" HDC_PATH="$HDC_PATH" HARMONY_HDC="$HARMONY_HDC" \
      BREAKPAD_GIT_REPO="$BREAKPAD_GIT_REPO" BREAKPAD_GIT_REVISION="$BREAKPAD_GIT_REVISION" \
  ./gradlew $GRADLE_VERSION_OPTS \
      -Pkotlin.native.enabled=true \
      -Pbootstrap.local=true \
      --dependency-verification=off \
      -PbreakpadGitRepo="$BREAKPAD_GIT_REPO" \
      -PbreakpadGitRevision="$BREAKPAD_GIT_REVISION" \
      $DFX_EXCLUDE_NAPI_OPTS \
      "$@"
}

TEST_EXIT_CODE=0
echo ""
if [ "$SKIP_COMPILE" = "true" ]; then
    echo "🚀 Running DFX tests for $TEST_TARGET (use cache; -c to force recompile)..."
    echo ""
    set +e
    eval "GRADLE_NATIVE :native:native.tests:dfxTest $GRADLE_TESTS_OPTS_STR -Pkn.target=\"$TEST_TARGET\" $FORCE_STANDALONE"
    TEST_EXIT_CODE=$?
    set -e
else
    echo "🚀 Running DFX tests for $TEST_TARGET (force recompilation)..."
    echo "🧹 Cleaning test cache..."
    rm -rf native/native.tests/build/t/bb.out native/native.tests/build/classes
    set +e
    eval "GRADLE_NATIVE :native:native.tests:dfxTest $GRADLE_TESTS_OPTS_STR -Pkn.target=\"$TEST_TARGET\" --no-configuration-cache --rerun-tasks $FORCE_STANDALONE"
    TEST_EXIT_CODE=$?
    set -e
fi

TEST_EXIT_CODE=${TEST_EXIT_CODE:-1}

echo ""
echo "========================================"
echo "DFX Test Summary — exit code: $TEST_EXIT_CODE ($(date))"
echo "========================================"
echo ""
echo "💡 Tips:"
echo "   ./scripts/test-dfx.sh                              # Default: $DEFAULT_TEST_NAME"
echo "   ./scripts/test-dfx.sh all                          # All tests under testData/dfx"
echo "   ./scripts/test-dfx.sh -c ReportBacktraceToOhosLogTest"
echo "   ./scripts/test-dfx.sh -r                           # Regenerate OhosDFXTestGenerated"
echo "   ./scripts/test-dfx.sh -v all                       # Emulator-supported tests only"
if [ -n "$HDC_PATH" ]; then
  echo "   \$HDC_PATH shell \"hilog | grep ReportBacktrace\""
fi

exit $TEST_EXIT_CODE
