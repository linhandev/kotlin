# Copyright (C) 2025-2026 Huawei Device Co., Ltd.
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

JAVA_HOME=/Users/bytedance/.gradle/jdks/eclipse_adoptium-11-aarch64-os_x.2/jdk-11.0.29+7/Contents/Home
PATH=$JAVA_HOME/bin:$PATH
rm local.properties
./gradlew publish

touch local.properties
echo "kotlin.native.enabled=true" > local.properties
echo "bootstrap.local=true" >> local.properties
echo "kotlin.native.isNativeRuntimeDebugInfoEnabled=true" >> local.properties
#./gradlew :kotlin-native:dist
./gradlew :kotlin-native:dist :kotlin-native:distPlatformLibs
#./gradlew :kotlin-native:bundle
/Users/bytedance/code/Keels/kotlin/kotlin-native/tools/llvm_builder/llvm-distribution/bin/llvm-as compiler_interface.ll -o ./kotlin-native/dist/konan/targets/macos_arm64/native/compiler_interface.bc

rm local.properties
echo "kotlin.native.enabled=true" > local.properties
echo "kotlin.native.isNativeRuntimeDebugInfoEnabled=true" >> local.properties
