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
#
# Usage:
# 
# Remote build (publish to remote Maven repository):
#    export MAVEN_REPO_URL="https://maven.eazytec-cloud.com/nexus/repository/maven-releases/"
#    export MAVEN_REPO_ID="deploy-server"
#    export DEPLOY_VERSION="2.2.21-ez-001"
#    export MAVEN_USERNAME="your-username"
#    export MAVEN_PASSWORD="your-password"
#    bash scripts/build-ohos-remote.sh
#
# Environment variables:
#   - MAVEN_REPO_URL: Remote Maven repository URL (optional; default is set in script)
#   - MAVEN_REPO_ID: Maven server id in settings.xml (optional; default: deploy-server)
#   - DEPLOY_VERSION: Deployment version (required)
#   - MAVEN_USERNAME: Maven repository username (required)
#   - MAVEN_PASSWORD: Maven repository password (required)

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

# Maven repository configuration
MAVEN_REPO_URL=${MAVEN_REPO_URL:-"https://maven.eazytec-cloud.com/nexus/repository/maven-releases"}
MAVEN_REPO_ID=${MAVEN_REPO_ID:-"deploy-server"}
MAVEN_USERNAME=${MAVEN_USERNAME:-""}
MAVEN_PASSWORD=${MAVEN_PASSWORD:-""}

# Validate required environment variables
if [[ -z "$MAVEN_USERNAME" ]]; then
  echo "❌ Error: MAVEN_USERNAME is required."
  echo "   Please set: export MAVEN_USERNAME=\"your-username\""
  exit 1
fi

if [[ -z "$MAVEN_PASSWORD" ]]; then
  echo "❌ Error: MAVEN_PASSWORD is required."
  echo "   Please set: export MAVEN_PASSWORD=\"your-password\""
  exit 1
fi

echo "========================================"
echo "🚀 Build Config (Remote)"
echo "ROOT_DIR       = $ROOT_DIR"
echo "DEPLOY_VERSION = $DEPLOY_VERSION"
echo "ARCH           = $(uname -m)"
echo "MAVEN_REPO_URL = $MAVEN_REPO_URL"
echo "MAVEN_REPO_ID  = $MAVEN_REPO_ID"
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
  # Build Gradle command arguments
  local GRADLE_ARGS=(
    -PdeployVersion="$DEPLOY_VERSION"
    -Pversions.kotlin-native="$DEPLOY_VERSION"
    -PkonanVersion="$DEPLOY_VERSION"
    -Pkotlin.native.enabled=true
    --dependency-verification=off
  )
  
  # Use remote repository as bootstrap source (version uses DEPLOY_VERSION)
  GRADLE_ARGS+=(-Pbootstrap.kotlin.version="$DEPLOY_VERSION")
  GRADLE_ARGS+=(-Pbootstrap.kotlin.repo="$MAVEN_REPO_URL")
  # Add publishing related properties
  GRADLE_ARGS+=(-Pdeploy-url="$MAVEN_REPO_URL")
  GRADLE_ARGS+=(-Pkotlin.build.deploy-url="$MAVEN_REPO_URL")
  GRADLE_ARGS+=(-Pkotlin.build.deploy-username="$MAVEN_USERNAME")
  GRADLE_ARGS+=(-Pkotlin.build.deploy-password="$MAVEN_PASSWORD")
  
  ./gradlew "${GRADLE_ARGS[@]}" "$@"
}

# --- Main Build Script ---

readHostArch

# Prepare local.properties
if [[ -e "$ROOT_DIR/local.properties" ]]; then
  mv "$ROOT_DIR/local.properties" "$ROOT_DIR/local.properties.bk"
fi
echo "kotlin.build.isObsoleteJdkOverrideEnabled=true" >> "$ROOT_DIR/local.properties"

# Reuse project Maven settings.xml for deploy credentials
MVN_SETTINGS_FILE="$ROOT_DIR/libraries/maven-settings.xml"
if [[ ! -f "$MVN_SETTINGS_FILE" ]]; then
  echo "❌ Error: Maven settings file not found: $MVN_SETTINGS_FILE"
  exit 1
fi

# Stop existing daemons
./gradlew --stop

# Update versions in pom.xml
"$ROOT_DIR/libraries/mvnw" -DnewVersion="$DEPLOY_VERSION" -DgenerateBackupPoms=false -DprocessAllModules=true -f "$ROOT_DIR/libraries/pom.xml" versions:set

# 1. Build part of kotlin and publish it to the local maven repository and to build/repo directory
stepBegin "Build part of kotlin and publish it to the local maven repository and to build/repo directory"
# Build Gradle publishing arguments
PUBLISH_ARGS=(
  -Pkotlin.native.enabled=false
  -PdeployVersion="$DEPLOY_VERSION"
  -Pversions.kotlin-native="$DEPLOY_VERSION"
  -PkonanVersion="$DEPLOY_VERSION"
  -Pbootstrap.local=false
  -Pteamcity=true
)

# Add publishing related properties (publish to remote Maven repository)
PUBLISH_ARGS+=(-Pdeploy-url="$MAVEN_REPO_URL")
PUBLISH_ARGS+=(-Pkotlin.build.deploy-url="$MAVEN_REPO_URL")
PUBLISH_ARGS+=(-Pkotlin.build.deploy-username="$MAVEN_USERNAME")
PUBLISH_ARGS+=(-Pkotlin.build.deploy-password="$MAVEN_PASSWORD")

./gradlew "${PUBLISH_ARGS[@]}" publish publishToMavenLocal 
stepEnd

# 2. Build maven part and publish it to the same build/repo
stepBegin "Build maven part and publish it to the same build/repo"


# Build Maven command arguments
MVN_DEPLOY_ARGS=(
  -f "$ROOT_DIR/libraries/pom.xml"
  clean deploy
  -DskipTests
  -Dkotlin.build.deploy-username="$MAVEN_USERNAME"
  -Dkotlin.build.deploy-password="$MAVEN_PASSWORD"
  -DaltDeploymentRepository="$MAVEN_REPO_ID::default::$MAVEN_REPO_URL"
)

echo "   Deploying to remote repository: $MAVEN_REPO_URL (id: $MAVEN_REPO_ID)"

# Execute Maven deploy: server credentials are resolved from libraries/maven-settings.xml
"$ROOT_DIR/libraries/mvnw" -s "$MVN_SETTINGS_FILE" "${MVN_DEPLOY_ARGS[@]}"
stepEnd

# --- Critical Check: Skip BOM check for remote deployment ---
echo "🔍 Remote deployment mode. Skipping local BOM check."

# 3. Build & Publish Kotlin Native
stepBegin "Build Kotlin Native and publish it to the remote repository"
if [[ -d "./kotlin-native/dist" ]]; then
  rm -Rf ./kotlin-native/dist
fi
GRADLE_NATIVE :kotlin-native:clean :kotlin-native:bundle :kotlin-native:publishBundlePrebuiltPublicationToMavenRepository --refresh-dependencies
stepEnd

# Final cleanup is handled by 'trap' automatically.
cd - > /dev/null

ELAPSED=$(($(date +%s) - START_TIME))
echo ""
echo "🎉 All build steps SUCCEEDED."
echo "⏱️  Building took: $((ELAPSED/60)) minutes and $((ELAPSED%60)) seconds."

