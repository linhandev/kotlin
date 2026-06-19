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

package org.jetbrains.kotlin.konan.util

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.concurrent.*

typealias ProgressCallback = (url: String, currentBytes: Long, totalBytes: Long) -> Unit

class DependencyDownloader(
        var maxAttempts: Int = DEFAULT_MAX_ATTEMPTS,
        var attemptIntervalMs: Long = DEFAULT_ATTEMPT_INTERVAL_MS,
        customProgressCallback: ProgressCallback? = null
) {

    private val progressCallback = customProgressCallback ?: TODO()/* { url, currentBytes, totalBytes ->
        print("\nDownloading dependency: $url (${currentBytes.humanReadable}/${totalBytes.humanReadable}). ")
    }*/

    val executor = ExecutorCompletionService<Unit>(Executors.newSingleThreadExecutor(object : ThreadFactory {
        override fun newThread(r: Runnable?): Thread {
            val thread = Thread(r)
            thread.name = "konan-dependency-downloader"
            thread.isDaemon = true

            return thread
      }
    }))

    enum class ReplacingMode {
        /** Redownload the file and replace the existing one. */
        REPLACE,
        /** Throw FileAlreadyExistsException */
        THROW,
        /** Don't download the file and return the existing one*/
        RETURN_EXISTING
    }

    class HTTPResponseException(val url: URL, val responseCode: Int)
        : IOException("Server returned HTTP response code: $responseCode for URL: $url")

    class DownloadingProgress(@Volatile var currentBytes: Long) {
        fun update(readBytes: Int) { currentBytes += readBytes }
    }

    private fun HttpURLConnection.checkHTTPResponse(expected: Int, originalUrl: URL = url) {
        if (responseCode != expected) {
            throw HTTPResponseException(originalUrl, responseCode)
        }
    }

    private fun HttpURLConnection.checkHTTPResponse(originalUrl: URL, predicate: (Int) -> Boolean) {
        if (!predicate(responseCode)) {
            throw HTTPResponseException(originalUrl, responseCode)
        }
    }

    private fun doDownload(originalUrl: URL,
                           connection: URLConnection,
                           tmpFile: File,
                           currentBytes: Long,
                           totalBytes: Long,
                           append: Boolean) {
        val progress = DownloadingProgress(currentBytes)

        // TODO: Implement multi-thread downloading.
        executor.submit {
            connection.getInputStream().use { from ->
                FileOutputStream(tmpFile, append).use { to ->
                    val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                    var read = from.read(buffer)
                    while (read != -1) {
                        if (Thread.interrupted()) {
                            throw InterruptedException()
                        }
                        to.write(buffer, 0, read)
                        progress.update(read)
                        read = from.read(buffer)
                    }
                    if (progress.currentBytes != totalBytes) {
                        throw EOFException("The stream closed before end of downloading.")
                    }
                }
            }
        }

        var result: Future<Unit>?
        do {
            progressCallback(originalUrl.toString(), progress.currentBytes, totalBytes)
            result = executor.poll(1, TimeUnit.SECONDS)
        } while(result == null)
        progressCallback(originalUrl.toString(), progress.currentBytes, totalBytes)

        try {
            result.get()
        } catch (e: ExecutionException) {
            throw e.cause ?: e
        }
    }

    private fun resumeDownload(originalUrl: URL, originalConnection: HttpURLConnection, tmpFile: File) {
        originalConnection.connect()
        val totalBytes = originalConnection.contentLengthLong
        val currentBytes = tmpFile.length()
        if (currentBytes >= totalBytes || originalConnection.getHeaderField("Accept-Ranges") != "bytes") {
            // The temporary file is bigger then expected or the server doesn't support resuming downloading.
            // Download the file from scratch.
            doDownload(originalUrl, originalConnection, tmpFile, 0, totalBytes, false)
        } else {
            originalConnection.disconnect()
            val rangeConnection = originalUrl.openConnection() as HttpURLConnection
            rangeConnection.setRequestProperty("range", "bytes=$currentBytes-")
            rangeConnection.connect()
            rangeConnection.checkHTTPResponse(originalUrl) {
                it == HttpURLConnection.HTTP_PARTIAL || it == HttpURLConnection.HTTP_OK
            }
            doDownload(originalUrl, rangeConnection, tmpFile, currentBytes, totalBytes, true)
        }
    }

    private enum class TryDownloadResult {
        SUCCESS,
        NOT_FOUND,
    }

    /** Performs a single download attempt into [tmpFile]. */
    private fun tryDownload(url: URL, tmpFile: File): TryDownloadResult {
        val connection = url.openConnection()
        if (connection is HttpURLConnection) {
            connection.instanceFollowRedirects = true
            connection.connect()
            when (connection.responseCode) {
                HttpURLConnection.HTTP_NOT_FOUND -> {
                    connection.disconnect()
                    return TryDownloadResult.NOT_FOUND
                }
                HttpURLConnection.HTTP_OK -> {
                    if (tmpFile.exists()) {
                        resumeDownload(url, connection, tmpFile)
                    } else {
                        val totalBytes = connection.contentLengthLong
                        doDownload(url, connection, tmpFile, 0, totalBytes, false)
                    }
                    return TryDownloadResult.SUCCESS
                }
                else -> throw HTTPResponseException(url, connection.responseCode)
            }
        }

        connection.connect()
        val totalBytes = connection.contentLengthLong
        doDownload(url, connection, tmpFile, 0, totalBytes, false)
        return TryDownloadResult.SUCCESS
    }

    private fun prepareDownloadDestination(destination: File): File {
        check(!destination.isDirectory) {
            "The destination file is a directory: ${destination.canonicalPath}. Remove it and try again."
        }
        val tmpFile = File("${destination.canonicalPath}.$TMP_SUFFIX")
        check(!tmpFile.isDirectory) {
            "A temporary file is a directory: ${tmpFile.canonicalPath}. Remove it and try again."
        }
        return tmpFile
    }

    private fun downloadWithRetries(source: URL, destination: File, tmpFile: File): TryDownloadResult {
        println("Downloading dependency $source to $destination")

        var attempt = 1
        var waitTime = 0L
        val handleException = { e: Exception ->
            if (attempt >= maxAttempts) {
                throw e
            }
            attempt++
            waitTime += attemptIntervalMs
            println("Cannot download a dependency $source: $e\n" +
                    "Waiting ${waitTime.toDouble() / 1000} sec and trying again (attempt: $attempt/$maxAttempts).")
            // TODO: Wait better
            Thread.sleep(waitTime)
        }
        while (true) {
            try {
                when (tryDownload(source, tmpFile)) {
                    TryDownloadResult.SUCCESS -> return TryDownloadResult.SUCCESS
                    TryDownloadResult.NOT_FOUND -> return TryDownloadResult.NOT_FOUND
                }
            } catch (e: HTTPResponseException) {
                if (e.responseCode >= 500) {
                    // Retry server errors.
                    handleException(e)
                } else {
                    // Do not retry client errors.
                    throw e
                }
            } catch (e: IOException) {
                handleException(e)
            }
        }
    }

    private fun finalizeDownload(tmpFile: File, destination: File) {
        Files.move(tmpFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING)
        println("Done.")
    }

    /**
     * Downloads [destination] from the first URL in [sources] that serves the file.
     * A missing archive (HTTP 404) is treated as a normal outcome and triggers the next URL.
     */
    fun downloadFirstAvailable(sources: List<URL>, destination: File): URL {
        require(sources.isNotEmpty()) { "At least one repository URL is required" }

        if (destination.exists()) {
            return sources.first()
        }

        val tmpFile = prepareDownloadDestination(destination)

        for (source in sources) {
            tmpFile.delete()
            when (downloadWithRetries(source, destination, tmpFile)) {
                TryDownloadResult.SUCCESS -> {
                    finalizeDownload(tmpFile, destination)
                    return source
                }
                TryDownloadResult.NOT_FOUND -> Unit
            }
        }

        throw FileNotFoundException(
                "Dependency archive ${destination.name} is not found in any of the configured repositories:\n" +
                        sources.joinToString("\n") { "  $it" }
        )
    }

    /** Downloads a file from [source] url to [destination]. Returns [destination]. */
    fun download(source: URL,
                 destination: File,
                 replace: ReplacingMode = ReplacingMode.RETURN_EXISTING): File {

        if (destination.exists()) {
            when (replace) {
                ReplacingMode.RETURN_EXISTING -> return destination
                ReplacingMode.THROW -> throw FileAlreadyExistsException(destination)
                ReplacingMode.REPLACE -> Unit // Just continue with downloading.
            }
        }

        val tmpFile = prepareDownloadDestination(destination)
        when (downloadWithRetries(source, destination, tmpFile)) {
            TryDownloadResult.SUCCESS -> finalizeDownload(tmpFile, destination)
            TryDownloadResult.NOT_FOUND -> throw HTTPResponseException(source, HttpURLConnection.HTTP_NOT_FOUND)
        }
        return destination
    }

    private val Long.humanReadable: String
        get() {
            if (this < 0) {
                return "-"
            }
            if (this < 1024) {
                return "$this bytes"
            }
            val exp = (Math.log(this.toDouble()) / Math.log(1024.0)).toInt()
            val prefix = "kMGTPE"[exp-1]
            return "%.1f %siB".format(this / Math.pow(1024.0, exp.toDouble()), prefix)
        }

    companion object {
        const val DEFAULT_MAX_ATTEMPTS = 10
        const val DEFAULT_ATTEMPT_INTERVAL_MS = 3000L

        const val TMP_SUFFIX = "part"
    }
}
