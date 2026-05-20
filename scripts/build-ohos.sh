#!/bin/zsh

#
# Eazytec is pleased to support the open source community by making CPF-KMP-CMP available.
# Copyright (C) 2026 Eazytec. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


if [ "$(uname -s)" = "Linux" ]; then
echo "=== 当前环境变量 ==="
env | grep -E 'LD_LIBRARY_PATH|PATH|LD_PRELOAD'
echo "=== 修改前 LD_LIBRARY_PATH ==="
echo "${LD_LIBRARY_PATH:-<未设置>}"
# 正确追加 lib64 路径（示例）
export LD_LIBRARY_PATH=${LD_LIBRARY_PATH:+${LD_LIBRARY_PATH}:}/usr/lib/x86_64-linux-gnu
echo "=== 修改后 LD_LIBRARY_PATH ==="
echo "$LD_LIBRARY_PATH"
# 可选：优先使用系统 libstdc++
export LD_PRELOAD=/usr/lib/x86_64-linux-gnu/libstdc++.so.6
fi


set -e # Exit immediately on error

START_TIME=$(date +%s)

unset M2_HOME
unset MAVEN_HOME

# --- Configuration ---
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd -P)
ROOT_DIR=$(cd "$SCRIPT_DIR"/../ && pwd -P)
cd "$ROOT_DIR"

if [[ -n "${gitcode_user:-}" && -n "${gitcode_pwd:-}" ]]; then
  echo "Configuring git credentials for gitcode.com..."
  git config --global url."https://${gitcode_user}:${gitcode_pwd}@gitcode.com/".insteadOf "git@gitcode.com:"
fi

if [ -d "third-party/common-rt/.git" ]; then
    echo "Submodule third-party/common-rt already initialized, pulling latest..."
    git submodule update --recursive third-party/common-rt
else
    echo "Initializing submodule third-party/common-rt..."
    git submodule update --init --recursive third-party/common-rt
fi

# Settings
DEPLOY_VERSION=${DEPLOY_VERSION:-2.2.21-OH-001}
USE_CN_MIRROR=${USE_CN_MIRROR:-true}
CN_MIRROR_PROVIDER=${CN_MIRROR_PROVIDER:-aliyun}
BREAKPAD_GIT_REPO=${BREAKPAD_GIT_REPO:-https://gitee.com/mirrors/breakpad.git}
BREAKPAD_GIT_REVISION=${BREAKPAD_GIT_REVISION:-v2024.02.16}
MAVEN_MAX_RETRIES=${MAVEN_MAX_RETRIES:-3}
MAVEN_RETRY_DELAY_SECONDS=${MAVEN_RETRY_DELAY_SECONDS:-3}
CN_MIRROR_INIT_SCRIPT="$ROOT_DIR/.gradle-cn-mirror.init.gradle.kts"
CN_MAVEN_SETTINGS_FILE="$ROOT_DIR/.mvn/settings-cn-temp.xml"

echo "========================================"
echo "🚀 Build Config"
echo "ROOT_DIR       = $ROOT_DIR"
echo "DEPLOY_VERSION = $DEPLOY_VERSION"
echo "USE_CN_MIRROR  = $USE_CN_MIRROR"
echo "MIRROR_PROVIDER= $CN_MIRROR_PROVIDER"
echo "MAVEN_RETRIES  = $MAVEN_MAX_RETRIES"
if [[ -n "$BREAKPAD_GIT_REPO" ]]; then
  echo "BREAKPAD_REPO = $BREAKPAD_GIT_REPO"
fi
echo "BREAKPAD_REV  = $BREAKPAD_GIT_REVISION"
if [[ "$USE_CN_MIRROR" == "true" ]]; then
  echo "MAVEN_MIRROR   = (enabled, provider-based)"
fi
echo "ARCH           = $(uname -m)"
echo "USER           = $(whoami)"
echo "========================================"

# Check JDK 1.8
# Check JDK 1.8
if [ -z "$JDK_18" ]; then
  if [[ "$(uname -s)" == "Darwin" ]]; then
    export JDK_18=$(/usr/libexec/java_home -v 1.8 2>/dev/null)
  else
    # Try to auto-detect JDK 8 on Linux
    for jdk8 in /usr/lib/jvm/java-8-openjdk-* /usr/lib/jvm/java-1.8.0-openjdk-*; do
      if [ -d "$jdk8" ]; then
        export JDK_18="$jdk8"
        break
      fi
    done
  fi
fi

if [ -z "$JDK_18" ]; then
  echo "❌ Error: JDK 1.8 is required."
  echo "   Please install JDK 8 and run: export JDK_18=<path_to_jdk_8>"
  exit 1
fi

# Ensure Windows native headers are visible for Kotlin/Native clang in Git Bash
if [[ "$(uname -s)" == MINGW* ]] && { [ -z "${INCLUDE:-}" ] || [ -z "${LIB:-}" ]; }; then
  MSVC_ROOT="$VS_ROOT/VC/Tools/MSVC"
  WINSDK_INCLUDE_ROOT="$WINSDK_ROOT/Include"
  WINSDK_LIB_ROOT="$WINSDK_ROOT/Lib"

  if [ -d "$MSVC_ROOT" ] && [ -d "$WINSDK_INCLUDE_ROOT" ] && [ -d "$WINSDK_LIB_ROOT" ]; then
    MSVC_VER=$(ls -1 "$MSVC_ROOT" 2>/dev/null | sort -V | tail -n 1)
    SDK_VER=$(ls -1 "$WINSDK_INCLUDE_ROOT" 2>/dev/null | sort -V | tail -n 1)

    if [ -n "$MSVC_VER" ] && [ -n "$SDK_VER" ]; then
      MSVC_INCLUDE=$(cygpath -m "$MSVC_ROOT/$MSVC_VER/include")
      UCRT_INCLUDE=$(cygpath -m "$WINSDK_INCLUDE_ROOT/$SDK_VER/ucrt")
      UM_INCLUDE=$(cygpath -m "$WINSDK_INCLUDE_ROOT/$SDK_VER/um")
      SHARED_INCLUDE=$(cygpath -m "$WINSDK_INCLUDE_ROOT/$SDK_VER/shared")
      WINRT_INCLUDE=$(cygpath -m "$WINSDK_INCLUDE_ROOT/$SDK_VER/winrt")
      CPPWINRT_INCLUDE=$(cygpath -m "$WINSDK_INCLUDE_ROOT/$SDK_VER/cppwinrt")

      if [ -z "${INCLUDE:-}" ]; then
        export INCLUDE="$MSVC_INCLUDE;$UCRT_INCLUDE;$UM_INCLUDE;$SHARED_INCLUDE;$WINRT_INCLUDE;$CPPWINRT_INCLUDE"
        echo "Auto-configured INCLUDE for Windows native toolchain."
      fi

      MSVC_LIB=$(cygpath -m "$MSVC_ROOT/$MSVC_VER/lib/x64")
      UCRT_LIB=$(cygpath -m "$WINSDK_LIB_ROOT/$SDK_VER/ucrt/x64")
      UM_LIB=$(cygpath -m "$WINSDK_LIB_ROOT/$SDK_VER/um/x64")

      if [ -z "${LIB:-}" ]; then
        export LIB="$MSVC_LIB;$UCRT_LIB;$UM_LIB"
        echo "Auto-configured LIB for Windows native toolchain."
      fi
    else
      echo "Error: Could not detect MSVC/Windows SDK versions. Native compilation may fail."
      exit 1
    fi
  else
    echo "Error: Visual Studio Build Tools MSVC: ${MSVC_ROOT} or "
    echo "  Windows SDK: ${WINSDK_INCLUDE_ROOT} or ${WINSDK_LIB_ROOT} directories not found."
    echo "Please set valid VS_ROOT and WINSDK_ROOT environment variables"
    echo "such as: export VS_ROOT=\"/c/Program Files/Microsoft Visual Studio/2022/Professional\""
    echo "and: export WINSDK_ROOT=\"/c/Program Files (x86)/Windows Kits/10\""
    echo "You can install visual studio build tools from the following links:"
    echo "https://visualstudio.microsoft.com/downloads/"
    echo "and install windows sdk by visual studio installer."
    exit 1
  fi
fi

# Maven wrapper requires unzip: without it, it downloads .tar.gz but validates with the .zip checksum → failure
if ! command -v unzip >/dev/null 2>&1; then
  echo "❌ Error: unzip is required."
  echo "   Please install unzip and re-run."
  exit 1
fi

STEP=1
STEP_MESSAGE=""

# --- Helper Functions ---

function stepBegin() {
  STEP_MESSAGE=$1
  echo ""
  echo ":::: Step $STEP: $STEP_MESSAGE"
  echo "----------------------------------------"
}

function stepEnd() {
  ((STEP++))
  echo "✅ Step $((STEP-1)) Completed."
}

function cleanUp() {
  echo ""
  echo "🧹 Performing cleanup..."
  if [[ -e "$ROOT_DIR/local.properties.bk" ]]; then
    mv "$ROOT_DIR/local.properties.bk" "$ROOT_DIR/local.properties"
    echo "   Restored local.properties."
  fi
  if [[ -e "$CN_MIRROR_INIT_SCRIPT" ]]; then
    rm -f "$CN_MIRROR_INIT_SCRIPT"
    echo "   Removed temp mirror init script."
  fi
  if [[ -e "$CN_MAVEN_SETTINGS_FILE" ]]; then
    rm -f "$CN_MAVEN_SETTINGS_FILE"
    echo "   Removed temp maven mirror settings."
  fi
  # Optional: Stop Gradle daemon on exit
}

# Register cleanUp to run on ANY exit
trap cleanUp EXIT

function readHostArch() {
  if [[ "$(uname -m)" == "arm64" ]]; then
      ARCH=aarch64
  else
      ARCH=x86_64
  fi
  echo "Build on $ARCH."
}

MIRROR_INIT_ARGS=()

function prepareCnMirrorConfig() {
  case "$CN_MIRROR_PROVIDER" in
    aliyun)
      CN_MAVEN_CENTRAL="https://maven.aliyun.com/repository/central"
      CN_GOOGLE="https://maven.aliyun.com/repository/google"
      CN_GRADLE_PLUGIN="https://maven.aliyun.com/repository/gradle-plugin"
      CN_MAVEN_MIRROR="https://maven.aliyun.com/repository/public"
      ;;
    tencent)
      CN_MAVEN_CENTRAL="https://mirrors.cloud.tencent.com/nexus/repository/maven-public/"
      CN_GOOGLE="https://mirrors.cloud.tencent.com/nexus/repository/google-maven/"
      CN_GRADLE_PLUGIN="https://mirrors.cloud.tencent.com/nexus/repository/gradle-plugins/"
      CN_MAVEN_MIRROR="https://mirrors.cloud.tencent.com/nexus/repository/maven-public/"
      ;;
    huawei)
      CN_MAVEN_CENTRAL="https://repo.huaweicloud.com/repository/maven/"
      CN_GOOGLE="https://repo.huaweicloud.com/repository/maven/google/"
      CN_GRADLE_PLUGIN="https://repo.huaweicloud.com/repository/maven/gradle-plugin/"
      CN_MAVEN_MIRROR="https://repo.huaweicloud.com/repository/maven/"
      ;;
    *)
      echo "❌ Error: unsupported CN_MIRROR_PROVIDER=$CN_MIRROR_PROVIDER"
      echo "   Supported values: aliyun, tencent, huawei"
      exit 1
      ;;
  esac
}

function prepareCnMirrorInitScript() {
  if [[ "$USE_CN_MIRROR" != "true" ]]; then
    return
  fi

  prepareCnMirrorConfig

  cat > "$CN_MIRROR_INIT_SCRIPT" <<EOF
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.net.URI

fun rewrite(url: String): String {
    val u = url.trimEnd('/')
    return when {
        u.startsWith("https://cache-redirector.jetbrains.com/repo1.maven.org/maven2") ||
        u.startsWith("https://cache-redirector.jetbrains.com/maven-central") ||
        u.startsWith("https://repo1.maven.org/maven2") ||
        u.startsWith("https://repo.maven.apache.org/maven2") -> "${CN_MAVEN_CENTRAL}"

        u.startsWith("https://cache-redirector.jetbrains.com/dl.google.com/dl/android/maven2") ||
        u.startsWith("https://cache-redirector.jetbrains.com/maven.google.com") ||
        u.startsWith("https://maven.google.com") ||
        u.startsWith("https://dl.google.com/dl/android/maven2") -> "${CN_GOOGLE}"

        u.startsWith("https://cache-redirector.jetbrains.com/plugins.gradle.org/m2") ||
        u.startsWith("https://cache-redirector.jetbrains.com/plugins.gradle.org") ||
        u.startsWith("https://plugins.gradle.org/m2") ||
        u.startsWith("https://plugins.gradle.org") -> "${CN_GRADLE_PLUGIN}"

        else -> u
    }
}

fun rewriteRepos(repos: org.gradle.api.artifacts.dsl.RepositoryHandler) {
    repos.withType(MavenArtifactRepository::class.java).configureEach {
        val old = url.toString()
        val newUrl = rewrite(old)
        if (old != newUrl) {
            url = URI(newUrl)
        }
    }
}

gradle.settingsEvaluated {
    rewriteRepos(pluginManagement.repositories)
    rewriteRepos(dependencyResolutionManagement.repositories)
}

gradle.beforeProject {
    rewriteRepos(repositories)
    rewriteRepos(buildscript.repositories)
}
EOF

  MIRROR_INIT_ARGS=(--init-script "$CN_MIRROR_INIT_SCRIPT")
  echo "🌐 Enabled CN mirror rewrite via init script. provider=$CN_MIRROR_PROVIDER"
}

function prepareCnMirrorMavenSettings() {
  if [[ "$USE_CN_MIRROR" != "true" ]]; then
    return
  fi

  mkdir -p "$(dirname "$CN_MAVEN_SETTINGS_FILE")"

  cat > "$CN_MAVEN_SETTINGS_FILE" <<EOF
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <mirrors>
    <mirror>
      <id>cn-mirror</id>
      <name>CN mirror (${CN_MIRROR_PROVIDER})</name>
      <url>${CN_MAVEN_MIRROR}</url>
      <mirrorOf>*</mirrorOf>
    </mirror>
  </mirrors>
</settings>
EOF
}

function run_maven() {
  if [[ "$USE_CN_MIRROR" == "true" ]]; then
    "$ROOT_DIR/libraries/mvnw" -s "$CN_MAVEN_SETTINGS_FILE" "$@"
  else
    "$ROOT_DIR/libraries/mvnw" "$@"
  fi
}

function cleanDependencyCache() {
  echo "🧹 Cleaning dependency caches to avoid pollution..."

  # Clean Gradle project-level cache
  echo " attempt to remove $ROOT_DIR/.gradle."
  if [[ -d "$ROOT_DIR/.gradle" ]]; then
    rm -rf "$ROOT_DIR/.gradle"
    echo "   Removed project .gradle cache."
  fi

  # Clean Gradle global downloaded dependencies
  local gradleHome="${GRADLE_USER_HOME:-$HOME/.gradle}"
  echo " attempt to remove $gradleHome/caches/modules-2."
  if [[ -d "$gradleHome/caches/modules-2" ]]; then
    rm -rf "$gradleHome/caches/modules-2"
    echo "   Removed $gradleHome/caches/modules-2."
  fi

 echo "  attempt to remove $HOME/.m2/repository."
  if [[ -d "$HOME/.m2/repository" ]]; then
    rm -rf "$HOME/.m2/repository"
    echo "   Removed $HOME/.m2/repository."
  fi

  # Clean Kotlin Native toolchain cache
  local konanHome="${KONAN_DATA_DIR:-$HOME/.konan}"
   echo " attempt to remove $konanHome/dependencies."
  if [[ -d "$konanHome/dependencies" ]]; then
    rm -rf "$konanHome/dependencies"
    echo "   Removed $konanHome/dependencies."
  fi

  echo "✅ Dependency cache cleaned."
}

function cleanMavenStaleParts() {
  local repoDir="$HOME/.m2/repository"
  if [[ ! -d "$repoDir" ]]; then
    return
  fi

  local deletedPartCount=0
  local deletedLockCount=0

  while IFS= read -r partFile; do
    rm -f "$partFile"
    ((deletedPartCount++))
  done < <(command find "$repoDir" -type f -name '*.part' 2>/dev/null)

  while IFS= read -r lockFile; do
    rm -f "$lockFile"
    ((deletedLockCount++))
  done < <(command find "$repoDir" -type f -name '*.lock' 2>/dev/null)

  if (( deletedPartCount > 0 || deletedLockCount > 0 )); then
    echo "🧼 Cleared stale Maven temp files: .part=$deletedPartCount .lock=$deletedLockCount"
  fi
}

function run_maven_with_retry() {
  local attempt=1
  local maxAttempts=$MAVEN_MAX_RETRIES

  while (( attempt <= maxAttempts )); do
    if run_maven "$@"; then
      return 0
    fi

    if (( attempt == maxAttempts )); then
      echo "❌ Maven command failed after $attempt/$maxAttempts attempts."
      return 1
    fi

    echo "⚠️ Maven command failed (attempt $attempt/$maxAttempts), cleaning stale temp files and retrying in ${MAVEN_RETRY_DELAY_SECONDS}s..."
    cleanMavenStaleParts
    sleep "$MAVEN_RETRY_DELAY_SECONDS"
    ((attempt++))
  done
}

function run_gradle() {
  local GRADLE_ARGS=("${MIRROR_INIT_ARGS[@]}")

  if [[ -n "$BREAKPAD_GIT_REPO" ]]; then
    GRADLE_ARGS+=(-PbreakpadGitRepo="$BREAKPAD_GIT_REPO")
  fi
  if [[ -n "$BREAKPAD_GIT_REVISION" ]]; then
    GRADLE_ARGS+=(-PbreakpadGitRevision="$BREAKPAD_GIT_REVISION")
  fi

  ./gradlew "${GRADLE_ARGS[@]}" "$@"
}

function GRADLE_NATIVE() {
  # Build Gradle command arguments
  local GRADLE_ARGS=(
    -PdeployVersion="$DEPLOY_VERSION"
    -Pversions.kotlin-native="$DEPLOY_VERSION"
    -PkonanVersion="$DEPLOY_VERSION"
    -Pkotlin.native.enabled=true
    --dependency-verification=off
  )

  # Use local bootstrap
  GRADLE_ARGS+=(-Pbootstrap.kotlin.version="$DEPLOY_VERSION")
  GRADLE_ARGS+=(-Pbootstrap.local=true)
  GRADLE_ARGS+=(-Pbootstrap.local.version="$DEPLOY_VERSION")

  run_gradle "${GRADLE_ARGS[@]}" "$@"
}

# --- Main Build Script ---

readHostArch
prepareCnMirrorInitScript
prepareCnMirrorMavenSettings

# Prepare local.properties
if [[ -e "$ROOT_DIR/local.properties" ]]; then
  mv "$ROOT_DIR/local.properties" "$ROOT_DIR/local.properties.bk"
fi
echo "kotlin.build.isObsoleteJdkOverrideEnabled=true" >> "$ROOT_DIR/local.properties"

# Stop existing daemons
run_gradle --stop

# Clean dependency caches before build to avoid stale/polluted artifacts
cleanDependencyCache

# Update versions in pom.xml
run_maven_with_retry -DnewVersion=$DEPLOY_VERSION -DgenerateBackupPoms=false -DprocessAllModules=true -f "$ROOT_DIR/libraries/pom.xml" versions:set

# 1. Build part of kotlin and publish it to the local maven repository and to build/repo directory
stepBegin "Build part of kotlin and publish it to the local maven repository and to build/repo directory"
run_gradle \
  -Pkotlin.native.enabled=false \
  -PdeployVersion=$DEPLOY_VERSION \
  -Pversions.kotlin-native=$DEPLOY_VERSION \
  -PkonanVersion=$DEPLOY_VERSION \
  -Pbootstrap.local=false \
  -Pteamcity=true \
  publish publishToMavenLocal
stepEnd

# 2. Build maven part and publish it to the same build/repo
stepBegin "Build maven part and publish it to the same build/repo"
if [[ "$(uname -s)" == MINGW* ]] || [[ "$(uname -s)" == MSYS* ]] || [[ "$(uname -s)" == CYGWIN* ]]; then
  ROOT_DIR_WIN=$(cygpath -w "$ROOT_DIR" | sed 's/\\/\//g')
  MAVEN_DEPLOY_URL="file:///$ROOT_DIR_WIN/build/repo"
  echo "MAVEN_DEPLOY_URL=$MAVEN_DEPLOY_URL"
else
  MAVEN_DEPLOY_URL="file://$ROOT_DIR/build/repo"
fi
run_maven_with_retry \
  -f "$ROOT_DIR/libraries/pom.xml" \
  clean deploy \
  -Ddeploy-url="$MAVEN_DEPLOY_URL" \
  -DskipTests
stepEnd

# --- Critical Check: Verify BOM Existence ---
BOM_CHECK_PATH="$ROOT_DIR/build/repo/org/jetbrains/kotlin/kotlin-bom/$DEPLOY_VERSION/kotlin-bom-$DEPLOY_VERSION.pom"
if [[ ! -f "$BOM_CHECK_PATH" ]]; then
  echo "❌ Critical Error: Kotlin BOM was not found at expected path after Maven build:"
  echo "   Missing: $BOM_CHECK_PATH"
  echo "   Reason: The Maven 'deploy' step failed to output files to build/repo."
  exit 1
else
  echo "🔍 Verified: Kotlin BOM exists. Proceeding to Native build."
fi

# 3. Clean Kotlin Native
stepBegin "Clean Kotlin Native dist."
if [[ -d "./kotlin-native/dist" ]]; then
  rm -Rf ./kotlin-native/dist
fi
# Use --refresh-dependencies to force refresh cache
GRADLE_NATIVE :kotlin-native:clean --refresh-dependencies
# GRADLE_NATIVE :kotlin-native:clean
stepEnd

# 4. Bundle Compiler
stepBegin "Bundle Kotlin Native compiler."
run_gradle --stop
GRADLE_NATIVE :kotlin-native:bundle
stepEnd

# 5. Publish Compiler
stepBegin "Publish Kotlin Native compiler to local."
GRADLE_NATIVE :kotlin-native:publishBundlePrebuiltPublicationToMavenRepository
stepEnd

# Final cleanup is handled by 'trap' automatically.
cd - > /dev/null

ELAPSED=$(($(date +%s) - START_TIME))
echo ""
echo "🎉 All build steps SUCCEEDED."
echo "⏱️  Building took: $((ELAPSED/60)) minutes and $((ELAPSED%60)) seconds."
