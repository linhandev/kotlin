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

set -e # Exit immediately on error

START_TIME=$(date +%s)

unset M2_HOME
unset MAVEN_HOME

# --- Configuration ---
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd -P)
ROOT_DIR=$(cd "$SCRIPT_DIR"/../ && pwd -P)
cd "$ROOT_DIR"

# Settings
DEPLOY_VERSION=${DEPLOY_VERSION:-2.2.21-OH-001}

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
  
  ./gradlew "${GRADLE_ARGS[@]}" "$@"
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

# Update versions in pom.xml
"$ROOT_DIR/libraries/mvnw" -DnewVersion=$DEPLOY_VERSION -DgenerateBackupPoms=false -DprocessAllModules=true -f "$ROOT_DIR/libraries/pom.xml" versions:set

# 1. Build part of kotlin and publish it to the local maven repository and to build/repo directory
stepBegin "Build part of kotlin and publish it to the local maven repository and to build/repo directory"
./gradlew \
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
"$ROOT_DIR/libraries/mvnw" \
  -f "$ROOT_DIR/libraries/pom.xml" \
  clean deploy \
  -Ddeploy-url=file://$ROOT_DIR/build/repo \
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