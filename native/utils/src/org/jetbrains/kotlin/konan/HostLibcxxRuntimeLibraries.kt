/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan

/**
 * Sonames of libc++/libc++abi/libunwind shipped next to the host LLVM toolchain on Linux.
 * Used when unpacking JNI stub libs ([kotlinx.cinterop] host loader) and in Gradle
 * (`NativeDependenciesExtension.hostLibcxxRuntimeLibraries`, kotlin-native/build-tools).
 */
object HostLibcxxRuntimeLibraries {
    val SONAMES: List<String> = listOf("libc++.so.1", "libc++abi.so.1", "libunwind.so.1")
}
