#!/usr/bin/env bash
#
# Idempotent environment bootstrap for building Kotlin/Native for OpenHarmony (OHOS)
# via scripts/build-ohos.sh on the develop-2.2.21-OH branch.
#
# Installs the host tools that build-ohos.sh and the Kotlin/Native OHOS pipeline need
# but that are not present in the default Cloud Agent image:
#   - JDK 8            : required by build-ohos.sh as $JDK_18 (bootstrap/obsolete-jdk override)
#   - ninja + gn       : required by third-party/common-rt/build.sh (buildLibCrt task)
#   - unzip            : required by the Maven wrapper
#
# JDK 11/17 toolchains and the custom LLVM + OHOS sysroots are provisioned automatically
# by Gradle toolchain auto-download and the Kotlin/Native dependency downloader.

set -euo pipefail

JDK8_VERSION_DIR="/opt/java/jdk8"
JVM_LINK="/usr/lib/jvm/java-8-openjdk-amd64"
GN_BIN="/usr/local/bin/gn"

log() { echo ":::: [install] $*"; }

# --- APT packages (ninja, unzip, build tools) ---
log "Installing apt packages (ninja-build, unzip, build-essential, rsync, curl)"
sudo apt-get update -qq
sudo DEBIAN_FRONTEND=noninteractive apt-get install -y -qq \
  ninja-build unzip build-essential rsync curl ca-certificates

# --- JDK 8 (Temurin) required as $JDK_18 by build-ohos.sh ---
if [ ! -x "$JDK8_VERSION_DIR/bin/java" ]; then
  log "Installing Temurin JDK 8"
  tmp="$(mktemp -d)"
  curl -fsSL -o "$tmp/jdk8.tar.gz" \
    "https://api.adoptium.net/v3/binary/latest/8/ga/linux/x64/jdk/hotspot/normal/eclipse"
  sudo mkdir -p /opt/java
  sudo rm -rf "$JDK8_VERSION_DIR"
  extracted="$(mktemp -d)"
  sudo tar -xzf "$tmp/jdk8.tar.gz" -C "$extracted"
  sudo mv "$extracted"/jdk8u* "$JDK8_VERSION_DIR"
  rm -rf "$tmp" "$extracted"
else
  log "Temurin JDK 8 already present at $JDK8_VERSION_DIR"
fi

# Expose JDK 8 where build-ohos.sh auto-detects it (glob: /usr/lib/jvm/java-8-openjdk-*).
sudo mkdir -p /usr/lib/jvm
sudo ln -sfn "$JDK8_VERSION_DIR" "$JVM_LINK"
"$JVM_LINK/bin/java" -version

# --- gn (Google's meta-build tool) required by common-rt/build.sh ---
if [ ! -x "$GN_BIN" ]; then
  log "Installing gn"
  tmp="$(mktemp -d)"
  curl -fsSL -o "$tmp/gn.zip" \
    "https://chrome-infra-packages.appspot.com/dl/gn/gn/linux-amd64/+/latest"
  unzip -o "$tmp/gn.zip" -d "$tmp/gnbin" >/dev/null
  sudo install -m 0755 "$tmp/gnbin/gn" "$GN_BIN"
  rm -rf "$tmp"
else
  log "gn already present at $GN_BIN"
fi
gn --version
ninja --version

# --- common-rt submodule (source for the OHOS libcrt.so) ---
log "Syncing third-party/common-rt submodule"
git submodule update --init --recursive third-party/common-rt

log "Environment ready. Build with: JDK_18=$JVM_LINK bash scripts/build-ohos.sh"
