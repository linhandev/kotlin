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

# Exit immediately on error
$ErrorActionPreference = "Stop"

$START_TIME = Get-Date

<#
.SYNOPSIS
    Removes a directory recursively. On Windows, handles long paths by using
    robocopy mirror trick when Remove-Item fails (e.g. path too long).
#>
function Remove-DirectoryRecurseSafe {
    param([string]$Path)
    if (-not (Test-Path $Path)) { return }
    $ErrorActionPreference = "Continue"
    try {
        Remove-Item -Path $Path -Recurse -Force -ErrorAction Stop
    } catch {
        # Windows path-too-long or similar: use robocopy mirror trick
        $emptyDir = Join-Path ([System.IO.Path]::GetTempPath()) ([System.Guid]::NewGuid().ToString("N"))
        New-Item -ItemType Directory -Path $emptyDir -Force | Out-Null
        try {
            & robocopy $emptyDir $Path /MIR /NFL /NDL /NJH /NJS /NC /NS /NP 2>&1 | Out-Null
            Remove-Item -Path $Path -Recurse -Force -ErrorAction SilentlyContinue
        } finally {
            if (Test-Path $emptyDir) { Remove-Item -Path $emptyDir -Recurse -Force -ErrorAction SilentlyContinue }
        }
    }
    $ErrorActionPreference = "Stop"
}

$env:M2_HOME = $null
$env:MAVEN_HOME = $null

# --- Configuration ---
$SCRIPT_DIR = Split-Path -Parent $MyInvocation.MyCommand.Path
$ROOT_DIR = (Resolve-Path (Join-Path $SCRIPT_DIR "..")).Path
Set-Location $ROOT_DIR

# Settings
if (-not $env:DEPLOY_VERSION) { $env:DEPLOY_VERSION = "2.2.21-OH-001" }
$DEPLOY_VERSION = $env:DEPLOY_VERSION

# Host arch: Windows AMD64 -> x86_64, ARM64 -> aarch64
$rawArch = $env:PROCESSOR_ARCHITECTURE
if ($rawArch -eq "ARM64") { $ARCH = "aarch64" } else { $ARCH = "x86_64" }

Write-Host "========================================"
Write-Host "Build Config"
Write-Host "ROOT_DIR       = $ROOT_DIR"
Write-Host "DEPLOY_VERSION = $DEPLOY_VERSION"
Write-Host "ARCH           = $ARCH"
Write-Host "========================================"

# Check JDK 1.8
if (-not $env:JDK_18) {
    Write-Host "Error: JDK 1.8 is required."
    Write-Host "   Please install JDK 8 and set: `$env:JDK_18 = '<path_to_jdk_8>'"
    exit 1
}

$script:STEP = 1
$script:STEP_MESSAGE = ""

# --- Helper Functions ---
function StepBegin($msg) {
    $script:STEP_MESSAGE = $msg
    Write-Host ""
    Write-Host ":::: Step $script:STEP: $script:STEP_MESSAGE"
    Write-Host "----------------------------------------"
}

function StepEnd {
    Write-Host "Step $script:STEP Completed."
    $script:STEP++
}

function CleanUp {
    Write-Host ""
    Write-Host "Performing cleanup..."
    $lp = Join-Path $ROOT_DIR "local.properties"
    $lpbk = Join-Path $ROOT_DIR "local.properties.bk"
    if (Test-Path $lpbk) {
        Move-Item -Path $lpbk -Destination $lp -Force
        Write-Host "   Restored local.properties."
    }
    # Optional: Stop Gradle daemon on exit
}

# Register cleanUp to run on ANY exit
try {

    function ReadHostArch {
        Write-Host "Build on $ARCH."
    }

    # Build Gradle command arguments; use local bootstrap
    function Invoke-GradleNative {
        param([string[]]$Tasks)
        $gradleArgs = @(
            "--console=plain",
            "-PdeployVersion=$DEPLOY_VERSION",
            "-Pversions.kotlin-native=$DEPLOY_VERSION",
            "-PkonanVersion=$DEPLOY_VERSION",
            "-Pkotlin.native.enabled=true",
            "--dependency-verification=off",
            "-Pbootstrap.kotlin.version=$DEPLOY_VERSION",
            "-Pbootstrap.local=true",
            "-Pbootstrap.local.version=$DEPLOY_VERSION"
        )
        & .\gradlew.bat $gradleArgs $Tasks
        if ($LASTEXITCODE -ne 0) { throw "Gradle failed with exit code $LASTEXITCODE" }
    }

    # --- Main Build Script ---
    ReadHostArch

    # Prepare local.properties
    $lp = Join-Path $ROOT_DIR "local.properties"
    $lpbk = Join-Path $ROOT_DIR "local.properties.bk"
    if (Test-Path $lp) {
        Move-Item -Path $lp -Destination $lpbk -Force
    }
    Add-Content -Path $lp -Value "kotlin.build.isObsoleteJdkOverrideEnabled=true"

    # Stop existing daemons (with timeout to avoid hanging)
    $stopJob = Start-Job -ScriptBlock { param($r) Set-Location $r; & .\gradlew.bat --stop --console=plain 2>&1 } -ArgumentList $ROOT_DIR
    $null = Wait-Job $stopJob -Timeout 120
    if ($stopJob.State -eq 'Running') {
        Stop-Job $stopJob; Remove-Job $stopJob -Force
        Write-Host "Warning: gradlew --stop timed out (120s), continuing..."
    } else {
        Receive-Job $stopJob | Out-Host
        Remove-Job $stopJob -Force
    }

    # Update versions in pom.xml
    $mvnw = Join-Path $ROOT_DIR "libraries\mvnw.cmd"
    $libPom = Join-Path $ROOT_DIR "libraries\pom.xml"
    & $mvnw "-DnewVersion=$DEPLOY_VERSION" "-DgenerateBackupPoms=false" "-DprocessAllModules=true" "-f" $libPom "versions:set"
    if ($LASTEXITCODE -ne 0) { throw "Maven versions:set failed" }

    # 1. Build part of kotlin and publish it to the local maven repository and to build/repo directory
    StepBegin "Build part of kotlin and publish it to the local maven repository and to build/repo directory"
    & .\gradlew.bat --console=plain `
        "-Pkotlin.native.enabled=false" `
        "-PdeployVersion=$DEPLOY_VERSION" `
        "-Pversions.kotlin-native=$DEPLOY_VERSION" `
        "-PkonanVersion=$DEPLOY_VERSION" `
        "-Pbootstrap.local=false" `
        "-Pteamcity=true" `
        "publish" "publishToMavenLocal"
    if ($LASTEXITCODE -ne 0) { throw "Gradle publish failed" }
    StepEnd

    # 2. Build maven part and publish it to the same build/repo
    StepBegin "Build maven part and publish it to the same build/repo"
    # Do not use Resolve-Path: build\repo may not exist yet; use path string for file URL
    $repoPath = (Join-Path $ROOT_DIR "build\repo").Replace('\', '/')
    $fileUrl = "file:///$repoPath"
    & $mvnw "-f" $libPom "clean" "deploy" "-Ddeploy-url=$fileUrl" "-DskipTests"
    if ($LASTEXITCODE -ne 0) { throw "Maven deploy failed" }
    StepEnd

    # --- Critical Check: Verify BOM Existence ---
    $bomPath = Join-Path $ROOT_DIR "build\repo\org\jetbrains\kotlin\kotlin-bom\$DEPLOY_VERSION\kotlin-bom-$DEPLOY_VERSION.pom"
    if (-not (Test-Path $bomPath)) {
        Write-Host "Critical Error: Kotlin BOM was not found at expected path after Maven build:"
        Write-Host "   Missing: $bomPath"
        Write-Host "   Reason: The Maven 'deploy' step failed to output files to build/repo."
        exit 1
    }
    Write-Host "Verified: Kotlin BOM exists. Proceeding to Native build."

    # 3. Clean Kotlin Native
    StepBegin "Clean Kotlin Native dist."
    $nativeDist = Join-Path $ROOT_DIR "kotlin-native\dist"
    if (Test-Path $nativeDist) {
        Remove-DirectoryRecurseSafe -Path $nativeDist
    }
    # Use --refresh-dependencies to force refresh cache
    Invoke-GradleNative ":kotlin-native:clean", "--refresh-dependencies"
    StepEnd

    # 4. Bundle Compiler
    StepBegin "Bundle Kotlin Native compiler."
    $stopJob2 = Start-Job -ScriptBlock { param($r) Set-Location $r; & .\gradlew.bat --stop --console=plain 2>&1 } -ArgumentList $ROOT_DIR
    $null = Wait-Job $stopJob2 -Timeout 120
    if ($stopJob2.State -eq 'Running') { Stop-Job $stopJob2; Remove-Job $stopJob2 -Force } else { Remove-Job $stopJob2 -Force }
    Invoke-GradleNative ":kotlin-native:bundle"
    StepEnd

    # 5. Publish Compiler
    StepBegin "Publish Kotlin Native compiler to local."
    Invoke-GradleNative ":kotlin-native:publishBundlePrebuiltPublicationToMavenRepository"
    StepEnd

    # Final cleanup is handled by 'finally' block automatically.
    $ELAPSED = (Get-Date) - $START_TIME
    $mins = [int]$ELAPSED.TotalMinutes
    $secs = $ELAPSED.Seconds
    Write-Host ""
    Write-Host "All build steps SUCCEEDED."
    Write-Host "Building took: $mins minutes and $secs seconds."

} finally {
    CleanUp
}
