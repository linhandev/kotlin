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

SCRIPT_DIR=$(cd $(dirname $0) && pwd -P)
ROOT_DIR=$SCRIPT_DIR/../

set -ex

cd $ROOT_DIR
# package ohos runtime
./gradlew :kotlin-native:runtime:ohos_arm64Runtime -Pbootstrap.local=true -Pkotlin.native.enabled --dependency-verification=off
# remove old runtime in dist folder
rm -rf $ROOT_DIR/kotlin-native/dist/konan/targets/ohos_arm64/native/*
# copy bc from build output to dist
cp $ROOT_DIR/kotlin-native/runtime/build/bitcode/main/ohos_arm64/*.bc $ROOT_DIR/kotlin-native/dist/konan/targets/ohos_arm64/native/
