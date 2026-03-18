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
# CAPI test script: run Ohos CAPI tests in Kotlin/Native (optional force recompile and cache clean).
# POSIX sh compatible (run with sh or zsh).

set -e

# ========== Paths and global config ==========
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
ROOT_DIR=$(cd "$SCRIPT_DIR/../" && pwd)
cd "$ROOT_DIR"

DEPLOY_VERSION=${DEPLOY_VERSION:-2.2.21-OH-001}
TEST_TARGET=${TEST_TARGET:-ohos_arm64}

# Pass version -P only when DEPLOY_VERSION is set; otherwise use Gradle defaults.
GRADLE_VERSION_OPTS=""
if [ -n "$DEPLOY_VERSION" ]; then
  GRADLE_VERSION_OPTS="-PdeployVersion=$DEPLOY_VERSION -Pversions.kotlin-native=$DEPLOY_VERSION -PkonanVersion=$DEPLOY_VERSION -Pbootstrap.kotlin.version=$DEPLOY_VERSION -Pbootstrap.local.version=$DEPLOY_VERSION"
fi

# Space-separated list: tests supported on emulator
EMULATOR_SUPPORTED_TESTS="AIPTest AVCapabilityTest AVDemuxerTest AVMuxerTest AVSourceTest AbilityAccessControlTest AbilityBaseTest AssetApiTest AudioCodecTest AudioDecoderTest AudioEncoderTest BackgroundProcessManagerTest CertManagerTest CloudDiskTest CodecBaseTest CoreTest CryptoAsymCipherApiTest CryptoAsymKeyApiTest CryptoCommonApiTest CryptoDigestApiTest CryptoKdfApiTest CryptoKeyAgreementApiTest CryptoMacApiTest CryptoRandApiTest CryptoSignatureApiTest CryptoSymCipherApiTest CryptoSymKeyApiTest DeviceInfoTest DeviceManagerTest DlpPermissionApiTest EglTest EnvironmentTest FASTTest FileIOTest FileShareTest FileUriTest Gles3Test Gles31Test Gles32Test GlescommonTest HiCollieTest HiLogTest HitraceTest HuksKeyApiTest HuksParamSetApiTest I18nTest IconvTest InitTest InputMethodTest Kba_drmTest LocationTest MemoryTest ModuleInstallTest MtdTest Multimedia_DrmTest MuslMallocTest NativeColorSpaceManagerTest NetConnectionTest NetstackTest NeuralNetworkRuntimeTest OHAVSessionTest OHAudioSuiteTest OHAudioTest OHIPCErrorCodeTest OH_BatteryInfoTest OH_CommonEventTest OsAccountTest PreviewTest QoSTest PosixTest RDBTest SensorTest ServiceCollaborationTest SoundTest TelephonyTest TimeServiceTest TransientTaskTest VibratorTest VideoDecoderTest VideoEncoderTest VideoProcessingTest VideoTest WifiTest"

# ========== Minimum API level per test class ==========
# get_min_api TEST_NAME -> 17|18|20|21|22 (default 17). Edit case list when adding MIN_API_*.
get_min_api() {
  case "$1" in
    USBSerialDDKTest|SCSIPeripheralDDKTest|AVImageGeneratorTest|AVMetadataExtractorTest|AVRecorderTest|NetworkBoostTest|ResourceManagerTest) echo 18 ;;
    AIPTest|CryptoAsymCipherApiTest|CryptoKdfApiTest|CryptoKeyAgreementApiTest|CryptoMacApiTest|CryptoRandApiTest|DeviceManagerTest|NetstackTest|SecurityAntivirusTest|SecurityAuditTest|AVSinkBaseTest|AVTranscoderTest|ArkUI_RenderNodeUtilsTest|FIDO2Test|HandWriteTest|LowPowerAudioSinkTest|LowPowerVideoSinkTest|NativeFenceTest|RetrievalTest|TeeClientTest) echo 20 ;;
    CloudDiskTest|GameControllerTest) echo 21 ;;
    CertManagerTest|FASTTest|I18nTest|OHAudioSuiteTest|HuksExternalCryptoApiTest) echo 22 ;;
    *) echo 17 ;;
  esac
}

# Space-separated list: tests that only run on PC (use -p/--pc)
PC_ONLY_TESTS="HidDdkTest HuksExternalCryptoApiTest PreviewTest SCSIPeripheralDDKTest SecurityAntivirusTest SecurityAuditTest USBSerialDDKTest UsbDDKTest BaseDdkTest Kba_devicesTest"

# Helper: is $1 in space-separated list $2
_is_in_list() { case " $2 " in *" $1 "*) return 0;; *) return 1;; esac; }

# ========== Usage ==========
#   ./scripts/test-capi.sh [options] [testName|all]
#   - No options: run tests using build cache (fast)
#   -c/--compile: clean test cache, force recompile, then run
#   -r: only regenerate test sources (run once after adding new .kt tests)
#   -v all: emulator mode, run only tests in emulator-supported list
#   -v XxxTest: emulator mode, run that test; exit with error if not in list
#   -p/--pc: run only PC-only tests (with "all": only PC_ONLY_TESTS; with -v: intersection with emulator list)
#   -m/--mobile: run only mobile-capable tests (default; with "all": exclude PC_ONLY_TESTS; with -v: intersection)
#   -p -v all / -m -v all: intersection of (pc or mobile list) and emulator-supported list
#   -api<N>: (optional) target device API level (N >= 17, e.g. -api20, -api22). If omitted, no API filter—all tests run.
# Examples:
#   ./scripts/test-capi.sh all
#   ./scripts/test-capi.sh -v all
#   ./scripts/test-capi.sh -v ZlibTest
#   ./scripts/test-capi.sh -v PosixTest
#   ./scripts/test-capi.sh -p all                # Run only PC-only tests (same: --pc all)
#   ./scripts/test-capi.sh -m all                # Run mobile-capable tests (default; same: --mobile all)
#   ./scripts/test-capi.sh -p -v all             # PC tests ∩ emulator list
#   ./scripts/test-capi.sh -api20 -v all         # Emulator API 20: skip tests that require API 21/22
#   ./scripts/test-capi.sh -api22 all             # API 22: run all (no API filter)
#   ./scripts/test-capi.sh -c AVMuxerTest
#   ./scripts/test-capi.sh -r

# ========== Subcommand: regenerate test sources ==========
# Run once after adding new .kt tests; takes precedence over normal run.
if [ "$1" = "--regenerate" ] || [ "$1" = "-r" ] || [ "$1" = "regenerate" ]; then
    echo ""
    echo "🔄 Regenerating test files (OhosCAPITestGenerated.java)..."
    echo "   This will scan native/native.tests/testData/capi and regenerate test classes"
    echo ""
    
    # Check JDK 1.8
    if [ -z "$JDK_18" ] && [ "$(uname -s)" = "Darwin" ]; then
      export JDK_18=$(/usr/libexec/java_home -v 1.8 2>/dev/null)
    fi
    
    if [ -z "$JDK_18" ]; then
      echo "❌ Error: JDK 1.8 is required."
      echo "   Please install JDK 8 and run: export JDK_18=<path_to_jdk_8>"
      exit 1
    fi
    
    GRADLE_REGEN_OPTS=""
    [ -n "$DEPLOY_VERSION" ] && GRADLE_REGEN_OPTS="-PdeployVersion=$DEPLOY_VERSION -Pversions.kotlin-native=$DEPLOY_VERSION -PkonanVersion=$DEPLOY_VERSION -Pbootstrap.kotlin.version=$DEPLOY_VERSION -Pbootstrap.local.version=$DEPLOY_VERSION"
    _run_gradle_regen() {
      ./gradlew $GRADLE_REGEN_OPTS -Pkotlin.native.enabled=true -Pbootstrap.local=true --dependency-verification=off "$@"
    }
    
    _run_gradle_regen :native:native.tests:generateTests
    echo ""
    echo "✅ Test files regenerated."
    echo ""
    echo "📋 Usage: ./scripts/test-capi.sh [ -c ] [ all | TestName ] or -r"
    exit 0
fi

# ========== Parse run-test arguments ==========
# Options -c -m -v -p -api<N> can be in any order. The last non -api* argument is the scope: "all" or a test name.
SKIP_COMPILE=true
FORCE_COMPILE=false
TEST_INPUT=""
EMULATOR_MODE=false
RUN_MODE="mobile"
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
        -p|--pc)
            RUN_MODE="pc"
            ;;
        -m|--mobile)
            RUN_MODE="mobile"
            ;;
        -api*)
            TARGET_API="${arg#-api}"
            case "$TARGET_API" in
              [0-9]*) [ "$TARGET_API" -lt 17 ] 2>/dev/null && { echo "❌ Error: -api must be >= 17 (e.g. -api20, -api22)."; exit 1; } ;;
              *) echo "❌ Error: -api must be followed by a number (e.g. -api20)."; exit 1 ;;
            esac
            ;;
        *)
            ;;
    esac
done
# Last argument that is not -api* = scope (all or TestName)
TEST_INPUT=""
for arg in "$@"; do
    case "$arg" in
        -api*) ;;
        *) TEST_INPUT="$arg"
    esac
done

# ========== Determine test scope and JUnit filter ==========
if [ -n "$TEST_INPUT" ]; then
    if [ "$TEST_INPUT" = "all" ] || [ "$TEST_INPUT" = "--all" ] || [ "$TEST_INPUT" = "-a" ]; then
        # Run all CAPI tests
        TEST_FILTER="*OhosCAPITestGenerated*"
        FORCE_STANDALONE=""
        echo "========================================"
        echo "🧪 CAPI Test Config (All Tests Mode)"
        echo "ROOT_DIR       = $ROOT_DIR"
        echo "DEPLOY_VERSION = ${DEPLOY_VERSION:-(not set)}"
        echo "TEST_TARGET    = $TEST_TARGET"
        echo "TEST_FILTER    = $TEST_FILTER (All CAPI tests)"
        [ -n "$TARGET_API" ] && echo "TARGET_API     = $TARGET_API (exclude tests requiring API > $TARGET_API)"
        echo "ARCH           = $(uname -m)"
        echo "========================================"
    else
        TEST_FILE_NAME="$TEST_INPUT"
        TEST_FILTER="*test${TEST_FILE_NAME}*"
        FORCE_STANDALONE="-Pkotlin.internal.native.test.forceStandalone=true"
        echo "========================================"
        echo "🧪 CAPI Test Config (Single File Mode)"
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
    TEST_FILTER=${TEST_FILTER:-"*AbilityBaseTest*"}
    FORCE_STANDALONE=""
    echo "========================================"
    echo "🧪 CAPI Test Config"
    echo "ROOT_DIR       = $ROOT_DIR"
    echo "DEPLOY_VERSION = ${DEPLOY_VERSION:-(not set)}"
    echo "TEST_TARGET    = $TEST_TARGET"
    echo "TEST_FILTER    = $TEST_FILTER"
    [ -n "$TARGET_API" ] && echo "TARGET_API     = $TARGET_API"
    echo "ARCH           = $(uname -m)"
    echo "========================================"
fi

# ========== Discover all CAPI tests (for -m all = all minus PC_ONLY) ==========
ALL_CAPI_TESTS=""
MOBILE_TESTS=""
if [ -d "$ROOT_DIR/native/native.tests/testData/capi" ]; then
  for f in "$ROOT_DIR"/native/native.tests/testData/capi/*.kt; do
    [ -f "$f" ] || continue
    name=$(basename "$f" .kt)
    ALL_CAPI_TESTS="$ALL_CAPI_TESTS $name"
  done
  for t in $ALL_CAPI_TESTS; do
    _is_in_list "$t" "$PC_ONLY_TESTS" || MOBILE_TESTS="$MOBILE_TESTS $t"
  done
fi

# Count words in space-separated list
_count_words() { set -- $1; echo $#; }

# ========== Run mode (-p/--pc, -m/--mobile) + Emulator mode (-v): effective list and GRADLE_TESTS_OPTS ==========
GRADLE_TESTS_OPTS_STR=""
RUN_ALL=1
[ "$TEST_INPUT" = "all" ] || [ "$TEST_INPUT" = "--all" ] || [ "$TEST_INPUT" = "-a" ] && RUN_ALL=0

if [ "$RUN_ALL" = 0 ]; then
  # "all": build effective list by run mode, then intersect with emulator if -v
  if [ "$RUN_MODE" = "pc" ]; then
    EFFECTIVE_LIST="$PC_ONLY_TESTS"
  else
    EFFECTIVE_LIST="$MOBILE_TESTS"
  fi
  if [ "$EMULATOR_MODE" = true ]; then
    _intersect=""
    for t in $EFFECTIVE_LIST; do
      _is_in_list "$t" "$EMULATOR_SUPPORTED_TESTS" && _intersect="$_intersect $t"
    done
    EFFECTIVE_LIST="$_intersect"
  fi
  # Filter by target API when -api<N> is set
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
    echo "❌ No tests to run (empty list for RUN_MODE=$RUN_MODE, EMULATOR_MODE=$EMULATOR_MODE${TARGET_API:+, -api$TARGET_API})."
    exit 1
  fi
  if [ -n "$TARGET_API" ]; then
    echo "========================================"
    echo "📌 API filter: target API $TARGET_API (excluding tests that require API > $TARGET_API)"
    echo "========================================"
  fi
  if [ "$EMULATOR_MODE" = true ]; then
    echo "========================================"
    echo "📱 Emulator mode: running $_eff_count tests (${RUN_MODE} ∩ emulator list)"
    echo "========================================"
  else
    echo "========================================"
    echo "🖥 Run mode: $RUN_MODE ($_eff_count tests)"
    echo "========================================"
  fi
else
  # Single test: validate run mode and optionally emulator
  if [ "$RUN_MODE" = "pc" ]; then
    _is_in_list "$TEST_INPUT" "$PC_ONLY_TESTS" || {
      echo "❌ Test '$TEST_INPUT' is not in PC-only list. Use -m/--mobile to run it, or add to PC_ONLY_TESTS."
      exit 1
    }
  else
    if _is_in_list "$TEST_INPUT" "$PC_ONLY_TESTS"; then
      echo "❌ Test '$TEST_INPUT' is PC-only. Use -p/--pc to run it on PC."
      exit 1
    fi
  fi
  if [ "$EMULATOR_MODE" = true ]; then
    _is_in_list "$TEST_INPUT" "$EMULATOR_SUPPORTED_TESTS" || {
      echo "❌ Test '$TEST_INPUT' is not supported on emulator."
      echo "   Supported: $EMULATOR_SUPPORTED_TESTS"
      exit 1
    }
    echo "========================================"
    echo "📱 Emulator mode: single test $TEST_INPUT (supported)"
    echo "========================================"
  fi
  if [ -n "$TARGET_API" ]; then
    min=$(get_min_api "$TEST_INPUT")
    if [ "$min" -gt "$TARGET_API" ] 2>/dev/null; then
      echo "❌ Test '$TEST_INPUT' requires API $min, but -api$TARGET_API was specified. This test is not available on API $TARGET_API."
      exit 1
    fi
    echo "========================================"
    echo "📌 API filter: target API $TARGET_API ($TEST_INPUT min API $min)"
    echo "========================================"
  fi
  GRADLE_TESTS_OPTS_STR="--tests '$TEST_FILTER'"
fi

# ========== Environment: JDK 1.8 ==========
if [ -z "$JDK_18" ] && [ "$(uname -s)" = "Darwin" ]; then
  export JDK_18=$(/usr/libexec/java_home -v 1.8 2>/dev/null)
fi
if [ -z "$JDK_18" ]; then
  echo "❌ Error: JDK 1.8 is required. Install JDK 8 and: export JDK_18=<path_to_jdk_8>"
  exit 1
fi

# ========== Environment: HDC (HarmonyOS device connection) ==========
if [ -z "$HDC_PATH" ]; then
  for hdc_path in "$HOME/Library/OpenHarmony/Sdk/20/toolchains/hdc" "$HOME/Library/Huawei/Sdk/toolchains/hdc" "/Users/$USER/Library/OpenHarmony/Sdk/20/toolchains/hdc" "/Users/$USER/Library/Huawei/Sdk/toolchains/hdc"; do
    if [ -f "$hdc_path" ] && [ -x "$hdc_path" ]; then
      export HDC_PATH="$hdc_path"
      echo "🔍 Found hdc at: $HDC_PATH"
      break
    fi
  done
  
  if [ -z "$HDC_PATH" ] && [ -n "$HARMONY_HDC" ]; then
    if [ -f "$HARMONY_HDC/hdc" ] && [ -x "$HARMONY_HDC/hdc" ]; then
      export HDC_PATH="$HARMONY_HDC/hdc"
      echo "🔍 Found hdc from HARMONY_HDC: $HDC_PATH"
    elif [ -f "$HARMONY_HDC/toolchains/hdc" ] && [ -x "$HARMONY_HDC/toolchains/hdc" ]; then
      export HDC_PATH="$HARMONY_HDC/toolchains/hdc"
      echo "🔍 Found hdc from HARMONY_HDC/toolchains: $HDC_PATH"
    fi
  fi
fi

# Export HDC_PATH to Gradle (via org.gradle.jvmargs or environment)
if [ -n "$HDC_PATH" ]; then
  export HDC_PATH
  echo "✅ Using HDC_PATH: $HDC_PATH"
else
  echo "⚠️  HDC_PATH not set. OhosExecutor will try to find hdc automatically."
fi

# ========== libc++_shared.so  ===========================
# On API 17 emulators/devices the system libc++ may lack symbols and tests can fail with "symbol not found".
# Push an architecture-matching libc++_shared.so to the device first, e.g. (replace with your .so path):
#   $HDC_PATH file send /path/to/libc++_shared.so /data/local/tmp/libc++_shared.so
# You can copy the .so from the SDK toolchain, e.g. $SDK/toolchains/llvm/lib/aarch64-linux-ohos/libc++_shared.so

# ========== Gradle wrapper (pass JDK/HDC etc.) ==========
CAPI_EXCLUDE_NAPI_OPTS="-Pkotlin.native.runtime.excludeNapi=true"
GRADLE_NATIVE() {
  env JDK_18="$JDK_18" HDC_PATH="$HDC_PATH" HARMONY_HDC="$HARMONY_HDC" \
  ./gradlew $GRADLE_VERSION_OPTS \
      -Pkotlin.native.enabled=true \
      -Pbootstrap.local=true \
      --dependency-verification=off \
      $CAPI_EXCLUDE_NAPI_OPTS \
      "$@"
}

# --- Main Test Script ---

TEST_EXIT_CODE=0

# ========== Run tests (use cache OR clean + force recompile) ==========
echo ""
if [ "$SKIP_COMPILE" = "true" ]; then
    echo "🚀 Running CAPI tests for $TEST_TARGET (skip compilation, use cache)..."
    echo "----------------------------------------"
    echo "💡 Using cached compilation results. Use -c to force recompilation."
    echo ""
    set +e
    eval "GRADLE_NATIVE :native:native.tests:capiTest $GRADLE_TESTS_OPTS_STR -Pkn.target=\"$TEST_TARGET\" $FORCE_STANDALONE"
    TEST_EXIT_CODE=$?
    set -e
else
    echo "🚀 Running CAPI tests for $TEST_TARGET (force recompilation)..."
    echo "----------------------------------------"
    echo "🧹 Cleaning test cache (included in -c)..."
    rm -rf native/native.tests/build/t/bb.out native/native.tests/build/classes
    set +e
    eval "GRADLE_NATIVE :native:native.tests:capiTest $GRADLE_TESTS_OPTS_STR -Pkn.target=\"$TEST_TARGET\" --no-configuration-cache --rerun-tasks $FORCE_STANDALONE"
    TEST_EXIT_CODE=$?
    set -e
fi

TEST_EXIT_CODE=${TEST_EXIT_CODE:-1}


echo ""
echo "========================================"
echo "Test Execution Summary"
echo "========================================"
echo "Finished at: $(date)"
echo "Exit code: $TEST_EXIT_CODE"
echo "========================================"
echo ""
echo "✅ Test completed."
echo ""
echo "💡 Tips:"
echo "   ./scripts/test-capi.sh all              # Default -m: run all mobile-capable tests"
echo "   ./scripts/test-capi.sh -p all           # Run only PC-only tests (-p/--pc)"
echo "   ./scripts/test-capi.sh -v all           # Emulator: mobile ∩ emulator list"
echo "   ./scripts/test-capi.sh -p -v all        # Emulator: PC ∩ emulator list"
echo "   ./scripts/test-capi.sh -v ZlibTest      # Emulator: run one test (errors if not in list)"
echo "   ./scripts/test-capi.sh -c TestName      # Clean cache, force recompile, then run"
echo "   ./scripts/test-capi.sh -api20 -v all    # Run only tests supported on API 20 (skip API 21/22)"
echo "   ./scripts/test-capi.sh -r               # Regenerate test sources"
echo "   (DEPLOY_VERSION is optional; unset uses Gradle defaults)"
if [ -n "$HDC_PATH" ]; then
  echo ""
  echo "   - View HiLog for XXXTest:     \$HDC_PATH shell \"hilog | grep XXXTest\""
  echo "     (you can adjust grep pattern to match other CAPI tests)"
fi

exit $TEST_EXIT_CODE

