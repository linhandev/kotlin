#!/bin/zsh

#
# Tencent is pleased to support the open source community by making TDS-KuiklyBase available.
# Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
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

set -e # 遇到错误立即退出

START_TIME=$(date +%s)

# --- Configuration ---
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd -P)
ROOT_DIR=$(cd "$SCRIPT_DIR"/../ && pwd -P)
cd "$ROOT_DIR"

# Settings
DEPLOY_VERSION=${DEPLOY_VERSION:-2.2.21-ez-ohos-0001}

echo "========================================"
echo "🚀 Build Config"
echo "ROOT_DIR       = $ROOT_DIR"
echo "DEPLOY_VERSION = $DEPLOY_VERSION"
echo "ARCH           = $(uname -m)"
echo "========================================"

# Check JDK 1.8
if [ -z "$JDK_18" ] && [[ "$(uname -s)" == "Darwin" ]]; then
  export JDK_18=$(/usr/libexec/java_home -v 1.8 2>/dev/null)
fi

if [ -z "$JDK_18" ]; then
  echo "❌ Error: JDK 1.8 is required."
  echo "   Please install JDK 8 and run: export JDK_18=<path_to_jdk_8>"
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
  # Optional: Stop Gradle daemon on exit
  # ./gradlew --stop
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

function GRADLE_NATIVE() {
  # Added --refresh-dependencies to ensure local artifacts are found.
  ./gradlew \
      -PdeployVersion="$DEPLOY_VERSION" \
      -Pversions.kotlin-native="$DEPLOY_VERSION" \
      -PkonanVersion="$DEPLOY_VERSION" \
      -Pbootstrap.kotlin.version="$DEPLOY_VERSION" \
      -Pkotlin.native.enabled=true \
      -Pbootstrap.local=true \
      -Pbootstrap.local.version="$DEPLOY_VERSION" \
      --dependency-verification=off \
      "$@"
}

# --- Main Build Script ---

readHostArch

# Prepare local.properties
if [[ -e "$ROOT_DIR/local.properties" ]]; then
  mv "$ROOT_DIR/local.properties" "$ROOT_DIR/local.properties.bk"
fi
echo "kotlin.build.isObsoleteJdkOverrideEnabled=true" >> "$ROOT_DIR/local.properties"

# Stop existing daemons
./gradlew --stop

# 1. Publish Bootstrap Libs
stepBegin "Publish bootstrap Kotlin libs to local dir: 'build/repo'."
./gradlew publishToMavenLocal
./gradlew publish install \
    -Pkotlin.native.enabled=false \
    -PdeployVersion="$DEPLOY_VERSION" \
    -Pversions.kotlin-native="$DEPLOY_VERSION" \
    -PkonanVersion="$DEPLOY_VERSION" \
    -Pbootstrap.local=false
stepEnd

# 2. Build & Publish Maven Parts
stepBegin "Build maven part and publish to 'build/repo'."
# 更新版本号
"$ROOT_DIR/libraries/mvnw" -DnewVersion="$DEPLOY_VERSION" -DgenerateBackupPoms=false -DprocessAllModules=true -f "$ROOT_DIR/libraries/pom.xml" versions:set

# 关键修复：使用 deploy 而不是 install，并指定 altDeploymentRepository 指向 build/repo
"$ROOT_DIR/libraries/mvnw" \
  -f "$ROOT_DIR/libraries/pom.xml" \
  clean deploy \
  -DskipTests \
  -DaltDeploymentRepository="local::default::file://$ROOT_DIR/build/repo"
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
# 使用 --refresh-dependencies 强制刷新缓存
GRADLE_NATIVE :kotlin-native:clean --refresh-dependencies
stepEnd

# 4. Bundle Compiler
stepBegin "Bundle Kotlin Native compiler."
./gradlew --stop
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