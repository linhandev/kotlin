/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.target

import java.io.File

object KonanTestFixtures {
    /**
     * Locates kotlin-native home by walking up from [startDir] until `kotlin-native/konan/konan.properties` is found.
     */
    fun findKonanHome(startDir: File = File(System.getProperty("user.dir"))): String {
        var dir: File? = startDir
        while (dir != null) {
            val propertiesFile = File(dir, "kotlin-native/konan/konan.properties")
            if (propertiesFile.isFile) {
                return File(dir, "kotlin-native").absolutePath
            }
            dir = dir.parentFile
        }
        error("Cannot find kotlin-native/konan/konan.properties starting from $startDir")
    }
}
