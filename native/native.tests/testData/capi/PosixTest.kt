/*
 * Copyright (C) 2026 Eazytec. All rights reserved.
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

// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: --unresolved-symbols=ignore-all

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.cinterop.*
import platform.posix.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class PosixTest {

    private fun logLine(msg: String) = println(msg)

    // errno.h — 已有 posix 包装
    @Test
    fun testErrno_h() {
        val e = posix_errno()
        assertNotNull(e)
        logLine("posix_errno=$e")
    }

    // limits.h
    @Test
    fun testLimits_h() {
        assertTrue(INT_MAX > 0)
        logLine("INT_MAX=$INT_MAX")
    }

    // stddef.h — size_t / NULL
    @Test
    fun testStddef_h() {
        memScoped {
            val n: size_t = 42u
            assertTrue(n.toULong() == 42uL)
            logLine("size_t=$n")
        }
    }

    // stdint.h
    @Test
    fun testStdint_h() {
        val x: int32_t = 1
        assertEquals(1, x.toInt())
        logLine("int32_t=$x")
    }

    // stdio.h
    @Test
    fun testStdio_h() {
        assertNotNull(stdin)
        logLine("stdin=$stdin")
    }

    // stdlib.h
    @Test
    fun testStdlib_h() {
        val p = malloc(8uL)
        assertNotNull(p)
        free(p)
        logLine("malloc/free ok")
    }

    // string.h
    @Test
    fun testString_h() {
        val len = strlen("hello")
        assertEquals(5, len.toInt())
        logLine("strlen=$len")
    }

    // strings.h
    @Test
    fun testStrings_h() {
        assertEquals(0, strcasecmp("abc", "abc"))
        logLine("strcasecmp ok")
    }

    // time.h
    @Test
    fun testTime_h() {
        val t = time(null)
        assertTrue(t >= 0L)
        logLine("time=$t")
    }

    // unistd.h
    @Test
    fun testUnistd_h() {
        val pid = getpid()
        assertTrue(pid > 0)
        logLine("getpid=$pid")
    }

    // ctype.h
    @Test
    fun testCtype_h() {
        assertEquals(1, isalpha('a'.code))
        logLine("isalpha('a')=1")
    }

    // math.h
    @Test
    fun testMath_h() {
        val x = sin(0.0)
        assertEquals(0.0, x, 1e-10)
        logLine("sin(0)=$x")
    }

    // fcntl.h
    @Test
    fun testFcntl_h() {
        assertTrue(O_RDONLY >= 0)
        logLine("O_RDONLY=$O_RDONLY")
    }

    // signal.h
    @Test
    fun testSignal_h() {
        assertTrue(SIGINT > 0)
        logLine("SIGINT=$SIGINT")
    }

    // dlfcn.h
    @Test
    fun testDlfcn_h() {
        assertTrue(RTLD_NOW != 0)
        logLine("RTLD_NOW=$RTLD_NOW")
    }

    // sys/stat.h
    @Test
    fun testSys_stat_h() {
        assertTrue(S_IFREG != 0)
        logLine("S_IFREG=$S_IFREG")
    }

    // sys/socket.h
    @Test
    fun testSys_socket_h() {
        assertTrue(AF_INET > 0)
        assertTrue(SOCK_STREAM > 0)
        logLine("AF_INET=$AF_INET SOCK_STREAM=$SOCK_STREAM")
    }

    // netinet/in.h — 使用 def 里的 posix_htons
    @Test
    fun testNetinet_in_h() {
        val v = posix_htons(0x1234)
        assertNotNull(v)
        logLine("posix_htons=$v")
    }

    // sys/select.h — 使用 def 里的 posix_FD_*
    @Test
    fun testSys_select_h() {
        memScoped {
            val readFds = alloc<fd_set>()
            posix_FD_ZERO(readFds.ptr)
            posix_FD_SET(0, readFds.ptr)
            val r = posix_FD_ISSET(0, readFds.ptr)
            assertTrue(r != 0)
            logLine("FD_ZERO/FD_SET/FD_ISSET ok")
        }
    }

    // poll.h
    @Test
    fun testPoll_h() {
        assertTrue(POLLIN != 0)
        logLine("POLLIN=$POLLIN")
    }

    // pthread.h
    @Test
    fun testPthread_h() {
        val self = pthread_self()
        assertNotNull(self)
        logLine("pthread_self=$self")
    }

    // sched.h
    @Test
    fun testSched_h() {
        val r = sched_yield()
        assertTrue(r == 0 || r == -1)
        logLine("sched_yield=$r")
    }

    // sys/time.h
    @Test
    fun testSys_time_h() {
        memScoped {
            val tv = alloc<timeval>()
            val r = gettimeofday(tv.ptr, null)
            assertTrue(r == 0)
            logLine("gettimeofday ok")
        }
    }

    // sys/utsname.h
    @Test
    fun testSys_utsname_h() {
        memScoped {
            val buf = alloc<utsname>()
            val r = uname(buf.ptr)
            assertTrue(r == 0)
            logLine("uname ok")
        }
    }

    // netdb.h — h_errno 包装
    @Test
    fun testNetdb_h() {
        val e = posix_h_errno()
        logLine("posix_h_errno=$e")
    }

    // inttypes.h
    @Test
    fun testInttypes_h() {
        val x: intptr_t = 0L
        assertEquals(0L, x)
        logLine("intptr_t ok")
    }

    // errno.h（与 testErrno_h 重复，用 EINVAL 常量）
    @Test
    fun testErrno_constants() {
        assertTrue(EINVAL > 0)
        logLine("EINVAL=$EINVAL")
    }

    // paths.h
    @Test
    fun testPaths_h() {
        assertNotNull(_PATH_MAILDIR)
        logLine("_PATH_MAILDIR=$_PATH_MAILDIR")
    }

    // sys/ipc.h
    @Test
    fun testSys_ipc_h() {
        assertTrue(IPC_CREAT != 0)
        logLine("IPC_CREAT=$IPC_CREAT")
    }

    // net/if_arp.h
    @Test
    fun testNet_if_arp_h() {
        assertTrue(ARPHRD_ETHER != 0)
        logLine("ARPHRD_ETHER=$ARPHRD_ETHER")
    }

    // netinet/in.h — INADDR_ANY
    @Test
    fun testNetinet_in_addr() {
        assertEquals(0u, INADDR_ANY)
        logLine("INADDR_ANY=$INADDR_ANY")
    }

    // locale.h
    @Test
    fun testLocale_h() {
        assertTrue(LC_ALL >= 0)
        logLine("LC_ALL=$LC_ALL")
    }

    // getopt.h
    @Test
    fun testGetopt_h() {
        assertTrue(optind >= 0)
        logLine("optind=$optind")
    }

    // libgen.h — basename 需要可写缓冲区指针
    @Test
    fun testLibgen_h() {
        memScoped {
            val buf = allocArray<ByteVar>(32)
            strcpy(buf, "/usr/bin/foo")
            val base = basename(buf)
            assertNotNull(base)
            logLine("basename=$base")
        }
    }
}
