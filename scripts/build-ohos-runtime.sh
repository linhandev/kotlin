SCRIPT_DIR=$(cd $(dirname $0) && pwd -P)
ROOT_DIR=$SCRIPT_DIR/../

export KONAN_DATA_DIR=${KONAN_DATA_DIR:-$ROOT_DIR/build/cache/konan}
export GRADLE_USER_HOME=${GRADLE_USER_HOME:-$ROOT_DIR/build/cache/gradle}

set -ex

cd $ROOT_DIR
# package ohos runtime
./gradlew :kotlin-native:runtime:ohos_arm64Runtime -Pbootstrap.local=true -Pkotlin.native.enabled --dependency-verification=off
# remove old runtime in dist folder
rm -rf $ROOT_DIR/kotlin-native/dist/konan/targets/ohos_arm64/native/*
# copy bc from build output to dist
cp $ROOT_DIR/kotlin-native/runtime/build/bitcode/main/ohos_arm64/*.bc $ROOT_DIR/kotlin-native/dist/konan/targets/ohos_arm64/native/
