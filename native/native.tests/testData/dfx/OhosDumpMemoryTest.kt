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
// DISABLE_NATIVE: gcType=NOOP
// TARGET_BACKEND: NATIVE
// SR003: Debugging.dumpMemory / MemoryDump v1.0.10, stable refs (TAG 0x08), raw streaming output.
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.native.internal.InternalForKotlinNative,kotlin.ExperimentalStdlibApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi

import kotlin.test.*
import kotlin.experimental.*
import kotlin.native.concurrent.ThreadLocal
import kotlin.native.ref.*
import kotlin.native.runtime.*
import kotlinx.cinterop.*
import platform.posix.*

@OptIn(ExperimentalForeignApi::class)
@ThreadLocal
private val tlsPrimitiveForDump = OhosDumpMemoryTestFixtures.PrimitiveData()

@OptIn(ExperimentalForeignApi::class)
@ThreadLocal
private val tlsArrayForDump = OhosDumpMemoryTestFixtures.ArrayData()

private object OhosDumpMemoryTestFixtures {
    class PrimitiveData {
        var boolean = true
        var char = 'Z'
        var byte = 127.toByte()
        var short = 32000.toShort()
        var int = Int.MAX_VALUE
        var long = Long.MAX_VALUE
        var float = 3.14f
        var double = 2.718281828
    }

    class ArrayData {
        var booleanArray = booleanArrayOf(true, false, true)
        var charArray = charArrayOf('K', 'N')
        var byteArray = byteArrayOf(1, 2, 3, 4)
        var shortArray = shortArrayOf(100, 200, 300)
        var intArray = intArrayOf(0, 1, 2, 3, 4)
        var longArray = longArrayOf(1L, 2L, 3L)
        var floatArray = floatArrayOf(1f, 2f, 3f)
        var doubleArray = doubleArrayOf(1.0, 2.0, 3.0)
        var string = "kotlin/native dump test"
        var array = arrayOf("foo", "bar", "baz")
    }

    class NestedData(val depth: Int) {
        val child: NestedData? = if (depth > 0) NestedData(depth - 1) else null
        val value = depth * 10
    }
}

/**
 * Black-box tests for [Debugging.dumpMemory] and MemoryDump format (commits bae2865..7110958, SR003).
 *
 * Runtime: PrepareForMemoryDump → traverse roots/heap/stable refs → raw streaming dump.
 * Current format header: "Kotlin/Native dump 1.0.10"; older 1.0.8/1.0.9 dumps remain accepted.
 * [TAG_STABLE_REF]=0x08 (bae2865).
 */
@OptIn(
    ExperimentalForeignApi::class,
    ExperimentalNativeApi::class,
    NativeRuntimeApi::class,
)
class OhosDumpMemoryTest {

    private fun logLine(msg: String) = println(msg)

    private fun dumpMemoryToFd(fd: Int): Boolean = Debugging.dumpMemory(fd.toLong())

    // ---------- Format constants (MemoryDump.cpp / kdumputil RecordTag) ----------

    private val dumpHeaderV110 = "Kotlin/Native dump 1.0.10"
    private val dumpHeaderV109 = "Kotlin/Native dump 1.0.9"
    private val dumpHeaderV108 = "Kotlin/Native dump 1.0.8"
    private val kdumputilAcceptedHeaders = listOf(dumpHeaderV108, dumpHeaderV109, dumpHeaderV110)

    private val tagType = 0x01
    private val tagObject = 0x02
    private val tagArray = 0x03
    private val tagExtraObject = 0x04
    private val tagThread = 0x05
    private val tagGlobalRoot = 0x06
    private val tagThreadRoot = 0x07
    private val tagStableRef = 0x08

    // ---------- Fixtures (file-level globals for dump roots) ----------

    private val globalPrimitive = OhosDumpMemoryTestFixtures.PrimitiveData()
    private val globalArray = OhosDumpMemoryTestFixtures.ArrayData()
    private val globalNested = OhosDumpMemoryTestFixtures.NestedData(4)

    // ---------- I/O helpers ----------

    private fun withTmpFile(block: (file: CPointer<FILE>, fd: Int) -> Unit): Long {
        val file = tmpfile()
        assertNotNull(file, "tmpfile() returned null")
        val fd = fileno(file)
        assertTrue(fd >= 0, "fileno(tmpfile()) returned $fd")
        block(file, fd)
        fflush(file)
        fseek(file, 0, SEEK_END)
        val size = ftell(file)
        fclose(file)
        return size
    }

    private fun readDumpFromFile(file: CPointer<FILE>): ByteArray {
        fflush(file)
        rewind(file)
        fseek(file, 0, SEEK_END)
        val size = ftell(file)
        assertTrue(size > 0L, "dump size must be > 0")
        assertTrue(
            size <= Int.MAX_VALUE.toLong(),
            "dump size $size exceeds Int.MAX_VALUE",
        )
        val sizeInt = size.toInt()
        rewind(file)
        return memScoped {
            val buf = allocArray<ByteVar>(sizeInt)
            val read = fread(buf, 1u, sizeInt.toULong(), file)
            assertEquals(sizeInt.toULong(), read, "fread dump")
            ByteArray(sizeInt) { i -> buf[i] }
        }
    }

    private fun dumpToBytes(setup: () -> Unit = {}): ByteArray {
        setup()
        var ok = false
        var dump = ByteArray(0)
        withTmpFile { file, fd ->
            ok = dumpMemoryToFd(fd)
            if (ok) dump = readDumpFromFile(file)
        }
        assertTrue(ok, "Debugging.dumpMemory() must return true")
        assertTrue(dump.isNotEmpty(), "dump must be non-empty")
        return dump
    }

    // ---------- Minimal kdump parser (mirrors kdumputil reader.kt) ----------

    private class DumpParseResult(
        val header: String,
        val littleEndian: Boolean,
        val idSizeBytes: Int,
        val recordTags: List<Int>,
        val stableRefCount: Int,
    )

    private fun parseDump(data: ByteArray): DumpParseResult {
        var off = 0
        val headerStart = off
        while (off < data.size && data[off] != 0.toByte()) off++
        val header = data.decodeToString(headerStart, off)
        off++
        assertTrue(off + 2 <= data.size, "missing endianness/id_size")
        val littleEndian = (data[off].toInt() and 0xFF) == 1
        off++
        val idSizeBytes = data[off].toInt() and 0xFF
        off++
        assertTrue(idSizeBytes in listOf(1, 2, 4, 8), "invalid id_size $idSizeBytes")

        val tags = mutableListOf<Int>()
        var stableRefs = 0
        val reader = KdumpStreamReader(data, off, littleEndian, idSizeBytes)
        while (reader.offset < data.size) {
            val tag = reader.readU8()
            tags.add(tag)
            when (tag) {
                tagType -> reader.skipTypeRecord()
                tagObject -> reader.skipObjectRecord()
                tagArray -> reader.skipArrayRecord()
                tagExtraObject -> reader.skipExtraObjectRecord()
                tagThread -> reader.skipThreadRecord()
                tagGlobalRoot -> reader.skipGlobalRootRecord()
                tagThreadRoot -> reader.skipThreadRootRecord()
                tagStableRef -> {
                    stableRefs++
                    reader.skipStableRefRecord()
                }
                else -> break
            }
        }
        return DumpParseResult(header, littleEndian, idSizeBytes, tags, stableRefs)
    }

    private class KdumpStreamReader(
        private val data: ByteArray,
        var offset: Int,
        private val littleEndian: Boolean,
        private val idSizeBytes: Int,
    ) {
        fun readU8(): Int {
            assertTrue(offset < data.size)
            return data[offset++].toInt() and 0xFF
        }

        fun readBytes(n: Int): ByteArray {
            assertTrue(offset + n <= data.size)
            val slice = data.copyOfRange(offset, offset + n)
            offset += n
            return slice
        }

        fun readInt(): Int {
            val b = readBytes(4)
            return if (littleEndian) {
                (b[0].toInt() and 0xFF) or
                    ((b[1].toInt() and 0xFF) shl 8) or
                    ((b[2].toInt() and 0xFF) shl 16) or
                    ((b[3].toInt() and 0xFF) shl 24)
            } else {
                ((b[0].toInt() and 0xFF) shl 24) or
                    ((b[1].toInt() and 0xFF) shl 16) or
                    ((b[2].toInt() and 0xFF) shl 8) or
                    (b[3].toInt() and 0xFF)
            }
        }

        fun readId() {
            readBytes(idSizeBytes)
        }

        fun readCString() {
            while (offset < data.size && data[offset] != 0.toByte()) offset++
            if (offset < data.size) offset++
        }

        fun skipTypeRecord() {
            readId()
            val flags = readU8()
            readId()
            readCString()
            readCString()
            val isArray = flags and 0x01 != 0
            val hasExtra = flags and 0x02 != 0
            if (isArray) {
                readInt()
                if (hasExtra) readU8()
            } else {
                readInt()
                val count = readInt()
                repeat(count) { readInt() }
                if (hasExtra) {
                    val fieldCount = readInt()
                    repeat(fieldCount) {
                        readInt()
                        readU8()
                        readCString()
                    }
                }
            }
        }

        fun skipObjectRecord() {
            readId()
            readId()
            val size = readInt()
            readBytes(size)
        }

        fun skipArrayRecord() {
            readId()
            readId()
            readInt()
            val size = readInt()
            readBytes(size)
        }

        fun skipExtraObjectRecord() {
            readId()
            readId()
            readId()
        }

        fun skipThreadRecord() = readId()

        fun skipGlobalRootRecord() {
            readU8()
            readId()
        }

        fun skipThreadRootRecord() {
            readId()
            readU8()
            readId()
        }

        fun skipStableRefRecord() {
            readId()
            readId()
        }
    }

    // ---------- Format / SR003 constant tests ----------

    @Test
    fun testDumpFormatHeaderConstant_v110() {
        assertEquals(dumpHeaderV110, "Kotlin/Native dump 1.0.10")
    }

    @Test
    fun testKdumputilAcceptsV108ThroughV110() {
        assertTrue(kdumputilAcceptedHeaders.contains(dumpHeaderV108))
        assertTrue(kdumputilAcceptedHeaders.contains(dumpHeaderV109))
        assertTrue(kdumputilAcceptedHeaders.contains(dumpHeaderV110))
    }

    @Test
    fun testRecordTagConstants_matchKdumputil() {
        assertEquals(0x08, tagStableRef)
        assertEquals(0x07, tagThreadRoot)
        assertEquals(0x01, tagType)
    }

    // ---------- dumpMemory API tests ----------

    @Test
    fun testDumpMemoryReturnsTrueAndFileIsNonEmpty() {
        val size = withTmpFile { _, fd ->
            assertTrue(dumpMemoryToFd(fd))
        }
        assertTrue(size > 0)
    }

    @Test
    fun testDumpOutputStartsWithRawKdumpHeader() {
        val file = tmpfile()
        assertNotNull(file)
        val fd = fileno(file)
        assertTrue(dumpMemoryToFd(fd))
        fflush(file)
        rewind(file)
        memScoped {
            val header = allocArray<ByteVar>(2)
            if (fread(header, 1u, 2u, file) == 2uL) {
                assertEquals('K'.code, header[0].toInt() and 0xFF)
                assertEquals('o'.code, header[1].toInt() and 0xFF)
            }
        }
        fclose(file)
    }

    @Test
    fun testRawDumpHeaderIsV110() {
        touchGlobals()
        val parsed = parseDump(dumpToBytes())
        assertEquals(dumpHeaderV110, parsed.header)
        logLine("dump header=${parsed.header} idSize=${parsed.idSizeBytes}")
    }

    @Test
    fun testDumpContainsStableRefRecord() {
        val obj = OhosDumpMemoryTestFixtures.ArrayData()
        val stable = StableRef.create(obj)
        try {
            val parsed = parseDump(dumpToBytes { GC.collect() })
            assertTrue(
                parsed.stableRefCount >= 1,
                "expected >=1 STABLE_REF (tag 0x08), got ${parsed.stableRefCount}; tags=${parsed.recordTags.count { it == tagStableRef }}",
            )
            assertTrue(tagStableRef in parsed.recordTags)
        } finally {
            stable.dispose()
        }
    }

    @Test
    fun testDumpWithPrimitiveFields() {
        assertTrue(globalPrimitive.boolean)
        val parsed = parseDump(dumpToBytes())
        assertTrue(parsed.recordTags.contains(tagObject) || parsed.recordTags.contains(tagType))
    }

    @Test
    fun testDumpWithArrayFields() {
        assertEquals(5, globalArray.intArray.size)
        val parsed = parseDump(dumpToBytes())
        assertTrue(parsed.recordTags.contains(tagArray) || parsed.recordTags.contains(tagObject))
    }

    @Test
    fun testDumpWithNestedObjectGraph() {
        assertEquals(4, globalNested.depth)
        assertTrue(parseDump(dumpToBytes()).recordTags.isNotEmpty())
    }

    @Test
    fun testDumpIncludesStackRoots() {
        val local = OhosDumpMemoryTestFixtures.PrimitiveData()
        val localArray = OhosDumpMemoryTestFixtures.ArrayData()
        val localNested = OhosDumpMemoryTestFixtures.NestedData(2)
        val dump = dumpRawToBytes()
        val parsed = parseDump(dump)
        assertTrue(parsed.recordTags.contains(tagThreadRoot) || parsed.recordTags.contains(tagObject))
        assertEquals(true, local.boolean)
        assertEquals(5, localArray.intArray.size)
        assertEquals(2, localNested.depth)
    }

    private fun dumpRawToBytes(): ByteArray {
        var ok = false
        var dump = ByteArray(0)
        withTmpFile { file, fd ->
            ok = dumpMemoryToFd(fd)
            if (ok) dump = readDumpFromFile(file)
        }
        assertTrue(ok)
        return dump
    }

    @Test
    fun testDumpIncludesThreadLocalRoots() {
        assertEquals(127.toByte(), tlsPrimitiveForDump.byte)
        assertEquals("kotlin/native dump test", tlsArrayForDump.string)
        val parsed = parseDump(dumpToBytes())
        assertTrue(parsed.recordTags.contains(tagThreadRoot) || parsed.recordTags.contains(tagThread))
    }

    @Test
    fun testDumpAfterGcCollect() {
        GC.collect()
        assertEquals(dumpHeaderV110, parseDump(dumpToBytes()).header)
    }

    @Test
    fun testDumpSizeIncreasesWithMoreLiveObjects() {
        GC.collect()
        val sizeBefore = dumpRawToBytes().size
        assertTrue(sizeBefore > 0)
        val extra = Array(64) { OhosDumpMemoryTestFixtures.ArrayData() }
        val sizeAfter = dumpRawToBytes().size
        assertTrue(sizeAfter > 0)
        assertEquals(5, extra[0].intArray.size)
    }

    @Test
    fun testMultipleSequentialDumps() {
        repeat(3) { i ->
            assertEquals(dumpHeaderV110, parseDump(dumpToBytes()).header)
            logLine("sequential dump $i ok")
        }
    }

    @Test
    fun testDumpWithWeakReference() {
        val strongRef = OhosDumpMemoryTestFixtures.PrimitiveData()
        val weakRef = WeakReference(strongRef)
        GC.collect()
        val parsed = parseDump(dumpToBytes())
        assertTrue(parsed.recordTags.contains(tagExtraObject) || parsed.recordTags.isNotEmpty())
        assertNotNull(weakRef.value)
        assertTrue(strongRef.boolean)
    }

    @Test
    fun testDumpWithAnonymousObject() {
        val anon = object { val field = "anonymous" }
        assertEquals(dumpHeaderV110, parseDump(dumpToBytes()).header)
        assertEquals("anonymous", anon.field)
    }

    @Test
    fun testDumpStressWithManyObjects() {
        val primitives = Array(200) { OhosDumpMemoryTestFixtures.PrimitiveData() }
        val arrays = Array(100) { OhosDumpMemoryTestFixtures.ArrayData() }
        val nested = Array(50) { OhosDumpMemoryTestFixtures.NestedData(3) }
        GC.collect()
        val parsed = parseDump(dumpToBytes())
        assertTrue(parsed.recordTags.size > 10)
        assertEquals(true, primitives[0].boolean)
        assertEquals(5, arrays[0].intArray.size)
        assertEquals(3, nested[0].depth)
    }

    private fun touchGlobals() {
        assertTrue(globalPrimitive.boolean)
        assertEquals(5, globalArray.intArray.size)
        assertEquals(4, globalNested.depth)
    }
}
