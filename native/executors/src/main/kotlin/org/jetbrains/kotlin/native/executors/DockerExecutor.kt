/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.native.executors

import org.jetbrains.kotlin.konan.target.HostManager
import org.jetbrains.kotlin.konan.target.KonanTarget
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * [Executor] that runs a Linux kexe in a persistent Docker container.
 *
 * - **Image.** Ubuntu 24.04 ([DEFAULT_IMAGE]). `linux_x64` → `--platform linux/amd64`,
 *       `linux_arm64` → `linux/arm64`.
 * - **Lifecycle.** First [execute] starts `docker run -d --rm --init … sleep` for
 *       [CONTAINER_TTL_SECONDS] (16h); later cases reuse it via `docker exec`.
 *       One container per target.
 * - **JVM crash.** Shutdown hook runs `docker rm -f`. SIGKILL skips the hook;
 *      `--rm` + TTL still drops the container.
 * - **Mount.** Same-path bind of Test worker `user.dir` ([bindMountRoot]).
 *      For `:native:native.tests:*` that is the `native.tests` project dir.
 * - **Permissions.** Host bind execute bits; no in-container `chmod`.
 * - **Exec.** Each case is `docker exec -i`. stdin / stdout / stderr / exit code
 *      pass through [HostExecutor] unchanged. Test env via `-e`.
 * - **Timeout.** Outer: [HostExecutor] kills the CLI at [ExecuteRequest.timeout]
 *      (`exitCode == null`), which does not kill the in-container process.
 *   Inner: `timeout -s KILL` at `request.timeout + 30s` so happy path timeout is still
 *      visible to executor.
 * - **Availability.** [availableFor]: mapped Linux target, host ≠ target (unless
 *      [forceRequested]), `docker` on PATH. Daemon is not probed.
 */
class DockerExecutor(
    private val target: KonanTarget,
    private val image: String = defaultImage(),
    private val platform: String = platformFor(target) ?: error("DockerExecutor does not support $target"),
    private val hostExecutor: Executor = HostExecutor(),
) : Executor {
    private val logger = Logger.getLogger(DockerExecutor::class.java.name)
    private val lock = Any()
    private val dockerAbsolutePath: String
        get() = dockerOnPath ?: error("docker not found on PATH")

    @Volatile
    private var containerId: String? = null

    override fun execute(request: ExecuteRequest): ExecuteResponse {
        val exeFile = File(request.executableAbsolutePath).canonicalFile
        val workDir = (request.workingDirectory ?: exeFile.parentFile).canonicalFile
        val cid = ensureContainer()

        val dockerArgs = mutableListOf(
            "exec",
            "-i",
            "-w", workDir.absolutePath,
        )
        for ((key, value) in request.environment) {
            dockerArgs += listOf("-e", "$key=$value")
        }
        dockerArgs += cid
        // Coreutils `timeout` is a safety net: HostExecutor killing the CLI leaves the
        // in-container process running. Fire after [request.timeout] plus grace so the
        // outer wait still observes a timeout (`exitCode == null`) on the happy path.
        if (!request.timeout.isInfinite()) {
            val innerSeconds = (request.timeout + INNER_TIMEOUT_GRACE)
                .inWholeSeconds
                .coerceAtLeast(1)
            dockerArgs += listOf("timeout", "-s", "KILL", innerSeconds.toString())
        }
        dockerArgs += exeFile.absolutePath
        dockerArgs += request.args

        logger.info(
            "DockerExecutor $target ($platform): $dockerAbsolutePath ${dockerArgs.joinToString(" ")}"
        )

        // Fresh request: do not mutate [request.args] (data-class copy shares the MutableList).
        // Host env for the docker CLI stays inherited; test env is passed into the container via -e.
        return hostExecutor.execute(
            ExecuteRequest(
                executableAbsolutePath = dockerAbsolutePath,
                args = dockerArgs,
                workingDirectory = workDir,
                stdin = request.stdin,
                stdout = request.stdout,
                stderr = request.stderr,
                timeout = request.timeout,
            )
        )
    }

    private fun ensureContainer(): String {
        containerId?.let { return it }
        synchronized(lock) {
            containerId?.let { return it }
            val id = startContainer()
            containerId = id
            Runtime.getRuntime().addShutdownHook(Thread({ destroyContainer(id) }, "DockerExecutor-rm-$id"))
            return id
        }
    }

    private fun startContainer(): String {
        val mount = bindMountRoot()
        val name = "kn-exec-${System.nanoTime().toString(36)}"
        val args = listOf(
            "run", "-d", "--rm", "--init",
            "--platform", platform,
            "--label", CONTAINER_LABEL,
            "--name", name,
            "-v", "${mount.absolutePath}:${mount.absolutePath}",
            image, "sleep", CONTAINER_TTL_SECONDS.toString(),
        )

        logger.info("DockerExecutor starting persistent container for $target ($platform): $dockerAbsolutePath ${args.joinToString(" ")}")
        val result = hostExecutor.runProcess(dockerAbsolutePath, *args.toTypedArray()) {
            timeout = CONTAINER_START_TIMEOUT
        }
        val id = result.stdout.trim()
        require(id.isNotEmpty()) { "docker run -d produced empty container id. stderr: ${result.stderr}" }
        logger.info("DockerExecutor container $id ($name) ready, mount=$mount")
        return id
    }

    private fun destroyContainer(id: String) {
        try {
            val nullFile = if (System.getProperty("os.name").orEmpty().startsWith("Windows")) File("NUL") else File("/dev/null")
            val process = ProcessBuilder(dockerAbsolutePath, "rm", "-f", id)
                .redirectOutput(ProcessBuilder.Redirect.to(nullFile))
                .redirectError(ProcessBuilder.Redirect.to(nullFile))
                .start()
            if (!process.waitFor(15, TimeUnit.SECONDS)) {
                process.destroyForcibly()
            }
        } catch (_: Exception) {
            // Best-effort: `--rm` + sleep TTL still bounds a leaked container.
        }
    }

    companion object {
        const val DEFAULT_IMAGE = "ubuntu:24.04"
        const val CONTAINER_LABEL = "kn-test-executor=1"
        const val CONTAINER_TTL_SECONDS = 16 * 3600L

        private val INNER_TIMEOUT_GRACE = 30.seconds
        private val CONTAINER_START_TIMEOUT = 2.minutes

        fun defaultImage(): String =
            System.getProperty("kotlin.internal.native.test.dockerImage") ?: DEFAULT_IMAGE

        /**
         * `--platform` for [target], or `null` if unsupported.
         * `linux_x64` → `linux/amd64`, `linux_arm64` → `linux/arm64`.
         */
        fun platformFor(target: KonanTarget): String? = when (target) {
            KonanTarget.LINUX_X64 -> "linux/amd64"
            KonanTarget.LINUX_ARM64 -> "linux/arm64"
            else -> null
        }

        /**
         * Gradle Test worker cwd (`user.dir`). Native tests with `test-inputs-check`
         * use the `native.tests` project directory, which contains `build/` kexe output.
         */
        fun bindMountRoot(): File = File(System.getProperty("user.dir")).canonicalFile

        /**
         * Perf-only: run Linux kexe in Docker even when host == target.
         * `KN_NATIVE_TEST_FORCE_DOCKER=1` or `-Dkotlin.internal.native.test.forceDocker=true`.
         */
        fun forceRequested(): Boolean =
            System.getProperty("kotlin.internal.native.test.forceDocker")?.equals("true", ignoreCase = true) == true ||
                System.getenv("KN_NATIVE_TEST_FORCE_DOCKER") == "1"

        /**
         * Supports [linux_x64][KonanTarget.LINUX_X64] or [linux_arm64][KonanTarget.LINUX_ARM64]
         * and when `docker` is on PATH. Does not probe the docker daemon.
         */
        fun availableFor(target: KonanTarget): Boolean {
            if (platformFor(target) == null) return false
            if (target == HostManager.host && !forceRequested()) return false
            return dockerOnPath != null
        }

        private val dockerOnPath: String? by lazy {
            for (dir in (System.getenv("PATH") ?: "").split(File.pathSeparatorChar)) {
                if (dir.isEmpty()) continue
                val candidate = File(dir, "docker")
                try {
                    if (candidate.isFile && candidate.canExecute()) return@lazy candidate.absolutePath
                } catch (_: SecurityException) {
                    // Skip PATH entries the SecurityManager does not allow reading.
                }
            }
            null
        }
    }
}
