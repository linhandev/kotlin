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
# 使用说明：
# 
# 远程构建（发布到远程 Maven 仓库）：
#    export MAVEN_REPO_URL="https://maven.eazytec-cloud.com/nexus/repository/maven-releases/"
#    export MAVEN_REPO_ID="nexus-releases"
#    export DEPLOY_VERSION="2.2.21-ez-001"
#    export DEPLOY_VERSION="2.2.21-ez-001" && export MAVEN_USERNAME="your-username" && export MAVEN_PASSWORD="your-password" && bash scripts/build-ohos-remote.sh
#
# 环境变量说明：
#   - MAVEN_REPO_URL: 远程 Maven 仓库 URL（必需）
#   - MAVEN_REPO_ID: Maven 仓库 ID（默认: nexus-releases）
#   - DEPLOY_VERSION: 部署版本号（必需）
#   - MAVEN_USERNAME: Maven 仓库用户名（必需）
#   - MAVEN_PASSWORD: Maven 仓库密码（必需）
#   - GRADLE_STOP_DAEMON: 是否在开始时停止 Gradle daemon（可选；默认 0，不停止以加速初始化）
#   - GRADLE_REFRESH_DEPS: 是否启用 --refresh-dependencies（可选；默认 0，不刷新以加速）

set -e # 遇到错误立即退出

START_TIME=$(date +%s)

unset M2_HOME
unset MAVEN_HOME

# --- Configuration ---
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd -P)
ROOT_DIR=$(cd "$SCRIPT_DIR"/../ && pwd -P)
cd "$ROOT_DIR"

# Settings
DEPLOY_VERSION=${DEPLOY_VERSION:-2.2.21-OH-001}

# Maven 仓库配置
MAVEN_REPO_URL=${MAVEN_REPO_URL:-"https://maven.eazytec-cloud.com/nexus/repository/maven-releases"}
MAVEN_REPO_ID=${MAVEN_REPO_ID:-"nexus-releases"}
MAVEN_USERNAME=${MAVEN_USERNAME:-""}
MAVEN_PASSWORD=${MAVEN_PASSWORD:-""}

# 验证必需的环境变量
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
echo "MAVEN_USERNAME = $MAVEN_USERNAME"
echo "GRADLE_STOP_DAEMON  = $GRADLE_STOP_DAEMON"
echo "GRADLE_REFRESH_DEPS = $GRADLE_REFRESH_DEPS"
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
  if [[ -n "$TMP_MAVEN_SETTINGS_FILE" && -f "$TMP_MAVEN_SETTINGS_FILE" ]]; then
    rm -f "$TMP_MAVEN_SETTINGS_FILE"
    echo "   Removed temp Maven settings."
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
  # 构建 Gradle 命令参数
  local GRADLE_ARGS=(
    -PdeployVersion="$DEPLOY_VERSION"
    -Pversions.kotlin-native="$DEPLOY_VERSION"
    -PkonanVersion="$DEPLOY_VERSION"
    -Pkotlin.native.enabled=true
    --dependency-verification=off
  )
  
  # 使用远程仓库作为 bootstrap 源（版本使用 DEPLOY_VERSION）
  GRADLE_ARGS+=(-Pbootstrap.kotlin.version="$DEPLOY_VERSION")
  GRADLE_ARGS+=(-Pbootstrap.kotlin.repo="$MAVEN_REPO_URL")
  # 添加发布相关属性
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



# 为 Maven deploy 生成临时 settings.xml（不落库，不依赖项目内 settings.xml）
TMP_MAVEN_SETTINGS_FILE="$(mktemp -t eazytec-mvn-settings.XXXXXX.xml)"
cat > "$TMP_MAVEN_SETTINGS_FILE" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
          http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>${MAVEN_REPO_ID}</id>
      <username>${MAVEN_USERNAME}</username>
      <password>${MAVEN_PASSWORD}</password>
    </server>
  </servers>
</settings>
EOF

./gradlew --stop

# 更新版本号
"$ROOT_DIR/libraries/mvnw" -DnewVersion="$DEPLOY_VERSION" -DgenerateBackupPoms=false -DprocessAllModules=true -f "$ROOT_DIR/libraries/pom.xml" versions:set

# 1. Publish Bootstrap Libs (publishToMavenLocal)
stepBegin "Publish bootstrap Kotlin libs to Maven Local (~/.m2/repository)."
./gradlew publishToMavenLocal

# 构建 Gradle 发布参数
PUBLISH_ARGS=(
  -Pkotlin.native.enabled=false
  -PdeployVersion="$DEPLOY_VERSION"
  -Pversions.kotlin-native="$DEPLOY_VERSION"
  -PkonanVersion="$DEPLOY_VERSION"
  --dependency-verification=off
)

# 使用远程仓库作为 bootstrap 源（版本使用 DEPLOY_VERSION）
PUBLISH_ARGS+=(-Pbootstrap.kotlin.version="$DEPLOY_VERSION")
PUBLISH_ARGS+=(-Pbootstrap.kotlin.repo="$MAVEN_REPO_URL")
# 添加发布相关属性
PUBLISH_ARGS+=(-Pdeploy-url="$MAVEN_REPO_URL")
PUBLISH_ARGS+=(-Pkotlin.build.deploy-url="$MAVEN_REPO_URL")
PUBLISH_ARGS+=(-Pkotlin.build.deploy-username="$MAVEN_USERNAME")
PUBLISH_ARGS+=(-Pkotlin.build.deploy-password="$MAVEN_PASSWORD")

echo "   Using remote repository for bootstrap: $MAVEN_REPO_URL"
echo "   Publishing to remote repository: $MAVEN_REPO_URL"

./gradlew "${PUBLISH_ARGS[@]}" publish install -x mvnPublish
stepEnd

# 2. Build & Publish Maven Parts
stepBegin "Build maven part and publish to remote repository: '$MAVEN_REPO_URL'."


# 构建 Maven 命令参数
MVN_DEPLOY_ARGS=(
  -f "$ROOT_DIR/libraries/pom.xml"
  clean deploy
  -DskipTests
  -DaltDeploymentRepository="$MAVEN_REPO_ID::default::$MAVEN_REPO_URL"
)

echo "   Deploying to remote repository: $MAVEN_REPO_URL (id: $MAVEN_REPO_ID)"
echo "   Using username: $MAVEN_USERNAME"

# 执行 Maven deploy：server 凭证必须来自 settings.xml，这里使用脚本生成的临时 settings.xml
"$ROOT_DIR/libraries/mvnw" -s "$TMP_MAVEN_SETTINGS_FILE" "${MVN_DEPLOY_ARGS[@]}"
stepEnd

# --- Critical Check: Skip BOM check for remote deployment ---
echo "🔍 Remote deployment mode. Skipping local BOM check."

# 3. Build & Publish Kotlin Native
stepBegin "Build & publish Kotlin Native (clean + bundle + publish)."
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

