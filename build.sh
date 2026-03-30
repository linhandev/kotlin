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
