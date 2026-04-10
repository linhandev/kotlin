/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.native.interop.indexer

import java.io.File

/** Maps absolute header paths to stable [HeaderId]s; first matching prefix in [headerIdRoots] wins, else content hash. */
class HeaderToIdMapper(headerIdRoots: List<String>) {
    private val headerPathToId = mutableMapOf<String, HeaderId>()
    private val roots = headerIdRoots.map { File(it).canonicalFile.toPath() }.distinct()

    init {
        require(roots.isNotEmpty()) { "headerIdRoots must not be empty" }
    }

    internal fun getHeaderId(filePath: String) = headerPathToId.getOrPut(filePath) {
        val path = File(filePath).canonicalFile.toPath()
        val headerIdValue = roots.firstOrNull { path.startsWith(it) }?.let { root ->
            root.relativize(path).toString()
        } ?: headerContentsHash(filePath)
        HeaderId(headerIdValue)
    }
}
