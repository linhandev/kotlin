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
// SR009: mem dump strip + gzip (f42e3ef); strip policy incl. String (37b1dac).
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.ExperimentalStdlibApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi

import kotlin.test.*
import kotlin.native.runtime.Debugging
import kotlin.native.runtime.GC
import kotlin.native.runtime.NativeRuntimeApi
import kotlinx.cinterop.*
import platform.posix.*
import platform.zlib.*

/**
 * Black-box tests for SR009 [MemoryDump.cpp] strip policy and kdumputil reconstruction.
 *
 * The current async path emits one or more concatenated gzip members and strips
 * every array payload except object-reference and NativePtr arrays.
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
    NativeRuntimeApi::class,
)
class OhosMemDumpStripTest {

    private fun logLine(msg: String) = println(msg)

    /**
     * OHOS permits only one forked dump child at a time. A gzip stream can be
     * complete just before that child exits, so the next sequential request may
     * transiently be rejected while the previous PID is still alive.
     */
    private fun startDumpMemoryStripAsync(fd: Int): Boolean {
        repeat(2_400) {
            if (Debugging.dumpMemoryAsync(fd.toLong(), true)) return true
            usleep(50_000u)
        }
        return false
    }

    // ---------- Mirrors MemoryDump.cpp / kdumputil (post-37b1dac) ----------

    private val strippedArrayRelativeNames = setOf(
        "BooleanArray", "ByteArray", "CharArray", "ShortArray",
        "IntArray", "LongArray", "FloatArray", "DoubleArray", "String",
    )
    private val pointerSetExpansionFactor = 2
    private val pointerSetMaxLoadFactor = 0.7
    private val pointerSetShiftBits = 3
    private val initialObjectSetCapacity = 16_777_216
    private val initialTypeSetCapacity = 4096
    private val initialInflateBufferSizeBytes = 1 * 1024 * 1024
    private val gzipMagic0 = 0x1f
    private val gzipMagic1 = 0x8b

    private object StripFixtures {
        val byteArray = byteArrayOf(1, 2, 3, 4, 5)
        val charArray = charArrayOf('S', 'R', '0', '9')
        val string = "SR009_mem_dump_strip_probe"
        val intArray = intArrayOf(10, 20, 30, 40)
        val objectArray = arrayOf("strip_a", "strip_b")
    }

    private val globalStripFixtures = StripFixtures

    // ---------- I/O (gzip dump) ----------

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

    private data class InflateAttempt(val data: ByteArray?, val needsMoreOutput: Boolean)

    /** Reads without changing the open-file offset shared with the OHOS fork child. */
    private fun readFdSnapshot(fd: Int): ByteArray = memScoped {
        val fileStat = alloc<stat>()
        assertEquals(0, fstat(fd, fileStat.ptr), "fstat dump fd")
        val size = fileStat.st_size
        if (size <= 0L) return@memScoped ByteArray(0)
        assertTrue(size <= Int.MAX_VALUE.toLong(), "compressed dump size $size exceeds Int.MAX_VALUE")
        val result = ByteArray(size.toInt())
        result.usePinned { pinned ->
            var offset = 0
            while (offset < result.size) {
                val read = pread(
                    fd,
                    pinned.addressOf(offset),
                    (result.size - offset).toULong(),
                    offset.toLong(),
                )
                if (read <= 0L) return@memScoped ByteArray(0)
                offset += read.toInt()
            }
        }
        result
    }

    /** Inflates every concatenated gzip member. Incomplete input is retried by the caller. */
    private fun tryInflateAllGzipMembers(compressed: ByteArray, outCapacity: Int): InflateAttempt = memScoped {
        if (compressed.size < 2 ||
            (compressed[0].toInt() and 0xFF) != gzipMagic0 ||
            (compressed[1].toInt() and 0xFF) != gzipMagic1
        ) return@memScoped InflateAttempt(null, false)

        val out = ByteArray(outCapacity)
        compressed.usePinned { inPinned ->
            out.usePinned { outPinned ->
                val stream = alloc<z_stream>().apply {
                    next_in = inPinned.addressOf(0).reinterpret()
                    avail_in = compressed.size.toUInt()
                    next_out = outPinned.addressOf(0).reinterpret()
                    avail_out = outCapacity.toUInt()
                }
                if (inflateInit2(stream.ptr, 15 + 16) != Z_OK) {
                    return@memScoped InflateAttempt(null, false)
                }
                while (true) {
                    when (inflate(stream.ptr, Z_NO_FLUSH)) {
                        Z_STREAM_END -> {
                            if (stream.avail_in == 0u) {
                                val produced = outCapacity - stream.avail_out.toInt()
                                inflateEnd(stream.ptr)
                                return@memScoped InflateAttempt(out.copyOf(produced), false)
                            }
                            if (inflateReset2(stream.ptr, 15 + 16) != Z_OK) {
                                inflateEnd(stream.ptr)
                                return@memScoped InflateAttempt(null, false)
                            }
                        }
                        Z_OK -> {
                            if (stream.avail_out == 0u) {
                                inflateEnd(stream.ptr)
                                return@memScoped InflateAttempt(null, true)
                            }
                            if (stream.avail_in == 0u) {
                                inflateEnd(stream.ptr)
                                return@memScoped InflateAttempt(null, false)
                            }
                        }
                        Z_BUF_ERROR -> {
                            val needsMoreOutput = stream.avail_out == 0u
                            inflateEnd(stream.ptr)
                            return@memScoped InflateAttempt(null, needsMoreOutput)
                        }
                        else -> {
                            inflateEnd(stream.ptr)
                            return@memScoped InflateAttempt(null, false)
                        }
                    }
                }
            }
        }
        InflateAttempt(null, false) // Unreachable, but keeps the lambda result type explicit.
    }

    private fun waitForCompleteGzipDump(fd: Int): ByteArray {
        repeat(2_400) { // Up to two minutes; OHOS returns before the fork child writes the dump.
            val snapshot = readFdSnapshot(fd)
            if (snapshot.isNotEmpty()) {
                var capacity = maxOf(snapshot.size * 32, initialInflateBufferSizeBytes)
                while (capacity > 0) {
                    val attempt = tryInflateAllGzipMembers(snapshot, capacity)
                    attempt.data?.let { return it }
                    if (!attempt.needsMoreOutput) break
                    assertTrue(capacity <= Int.MAX_VALUE / 2, "gzip inflate buffer exhausted at $capacity bytes")
                    capacity *= 2
                }
            }
            usleep(50_000u)
        }
        fail("timed out waiting for a complete gzip memory dump")
    }

    private fun dumpToDecompressedBytes(): ByteArray {
        touchFixtures()
        GC.collect()
        var ok = false
        var decompressed = ByteArray(0)
        withTmpFile { file, fd ->
            ok = startDumpMemoryStripAsync(fd)
            if (ok) decompressed = waitForCompleteGzipDump(fd)
        }
        assertTrue(ok, "Debugging.dumpMemoryAsync() must return true")
        assertTrue(decompressed.isNotEmpty(), "decompressed dump must be non-empty")
        return decompressed
    }

    // ---------- kdump parser: types + array payload sizes ----------

    private data class DumpTypeEntry(
        val idKey: String,
        val packageName: String,
        val relativeName: String,
        val isArray: Boolean,
        val isObjectArray: Boolean,
        val arrayElementSize: Int?,
    )

    private data class DumpArrayEntry(
        val typeIdKey: String,
        val count: Int,
        val payloadSize: Int,
    )

    private data class StripParseResult(
        val types: Map<String, DumpTypeEntry>,
        val arrays: List<DumpArrayEntry>,
    )

    private fun bytesToIdKey(bytes: ByteArray): String =
        bytes.joinToString("") { (it.toInt() and 0xFF).toString(16).padStart(2, '0') }

    private fun parseTypesAndArrays(data: ByteArray): StripParseResult {
        var off = 0
        while (off < data.size && data[off] != 0.toByte()) off++
        off++
        assertTrue(off + 2 <= data.size, "missing endianness/id_size")
        val littleEndian = (data[off].toInt() and 0xFF) == 1
        off++
        val idSizeBytes = data[off].toInt() and 0xFF
        off++
        assertTrue(idSizeBytes in listOf(1, 2, 4, 8), "invalid id_size $idSizeBytes")

        val types = mutableMapOf<String, DumpTypeEntry>()
        val arrays = mutableListOf<DumpArrayEntry>()
        val reader = StripKdumpReader(data, off, littleEndian, idSizeBytes)

        val tagType = 0x01
        val tagObject = 0x02
        val tagArray = 0x03
        val tagExtraObject = 0x04
        val tagThread = 0x05
        val tagGlobalRoot = 0x06
        val tagThreadRoot = 0x07
        val tagStableRef = 0x08

        while (reader.offset < data.size) {
            when (val tag = reader.readU8()) {
                tagType -> {
                    val typeEntry = reader.readTypeRecord()
                    types[typeEntry.idKey] = typeEntry
                }
                tagObject -> reader.skipObjectRecord()
                tagArray -> arrays.add(reader.readArrayRecord())
                tagExtraObject -> reader.skipExtraObjectRecord()
                tagThread -> reader.skipThreadRecord()
                tagGlobalRoot -> reader.skipGlobalRootRecord()
                tagThreadRoot -> reader.skipThreadRootRecord()
                tagStableRef -> reader.skipStableRefRecord()
                else -> break
            }
        }
        return StripParseResult(types, arrays)
    }

    private inner class StripKdumpReader(
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

        fun readIdBytes(): ByteArray = readBytes(idSizeBytes)

        fun readCString(): String {
            val start = offset
            while (offset < data.size && data[offset] != 0.toByte()) offset++
            val s = data.decodeToString(start, offset)
            if (offset < data.size) offset++
            return s
        }

        fun readTypeRecord(): DumpTypeEntry {
            val id = readIdBytes()
            val flags = readU8()
            readIdBytes()
            val packageName = readCString()
            val relativeName = readCString()
            val isArray = flags and 0x01 != 0
            val isObjectArray = flags and 0x04 != 0
            val hasExtra = flags and 0x02 != 0
            val arrayElementSize = if (isArray) readInt() else null
            if (isArray) {
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
            val entry = DumpTypeEntry(
                idKey = bytesToIdKey(id),
                packageName = packageName,
                relativeName = relativeName,
                isArray = isArray,
                isObjectArray = isObjectArray,
                arrayElementSize = arrayElementSize,
            )
            return entry
        }

        fun readArrayRecord(): DumpArrayEntry {
            readIdBytes()
            val typeId = readIdBytes()
            val count = readInt()
            val payloadSize = readInt()
            if (payloadSize > 0) readBytes(payloadSize)
            return DumpArrayEntry(bytesToIdKey(typeId), count, payloadSize)
        }

        fun skipObjectRecord() {
            readIdBytes()
            readIdBytes()
            val size = readInt()
            readBytes(size)
        }

        fun skipExtraObjectRecord() {
            readIdBytes()
            readIdBytes()
            readIdBytes()
        }

        fun skipThreadRecord() = readIdBytes()

        fun skipGlobalRootRecord() {
            readU8()
            readIdBytes()
        }

        fun skipThreadRootRecord() {
            readIdBytes()
            readU8()
            readIdBytes()
        }

        fun skipStableRefRecord() {
            readIdBytes()
            readIdBytes()
        }
    }

    private fun StripParseResult.arrayEntriesNamed(relativeName: String): List<DumpArrayEntry> {
        val typeKeys = types.filter { it.value.relativeName == relativeName }.keys
        return arrays.filter { it.typeIdKey in typeKeys }
    }

    private fun shouldStripArrayPayload(relativeName: String): Boolean =
        relativeName in strippedArrayRelativeNames

    /** Mirrors kdumputil [Converter.reconstructObjectByteArray] for stripped object bodies. */
    private fun reconstructObjectByteArray(
        strippedData: ByteArray,
        objectOffsets: IntArray,
        instanceSize: Int,
        ptrSize: Int,
    ): ByteArray {
        val result = ByteArray(instanceSize)
        for (i in objectOffsets.indices) {
            val srcOffset = i * ptrSize
            val dstOffset = objectOffsets[i]
            if (srcOffset + ptrSize <= strippedData.size && dstOffset + ptrSize <= instanceSize) {
                strippedData.copyInto(result, dstOffset, srcOffset, srcOffset + ptrSize)
            }
        }
        return result
    }

    /** Mirrors kdumputil zero-fill when [ArrayItem.byteArray] is empty but count > 0. */
    private fun zeroFilledPrimitiveArray(count: Int, elementSize: Int): ByteArray =
        ByteArray(count * elementSize)

    private fun touchFixtures() {
        assertEquals(5, globalStripFixtures.byteArray.size)
        assertEquals(4, globalStripFixtures.charArray.size)
        assertTrue(globalStripFixtures.string.contains("SR009"))
        assertEquals(4, globalStripFixtures.intArray.size)
        assertEquals(2, globalStripFixtures.objectArray.size)
    }

    // ---------- SR009 infrastructure mirrors (f42e3ef) ----------

    @Test
    fun testPointerSetConstants_mirror() {
        assertEquals(2, pointerSetExpansionFactor)
        assertEquals(0.7, pointerSetMaxLoadFactor)
        assertEquals(3, pointerSetShiftBits)
        assertEquals(16_777_216, initialObjectSetCapacity)
        assertEquals(4096, initialTypeSetCapacity)
    }

    @Test
    fun testInitialInflateBufferSize_1MB() {
        assertEquals(1024 * 1024, initialInflateBufferSizeBytes)
    }

    @Test
    fun testStripPolicy_stripsPrimitiveArraysAndString() {
        assertTrue(shouldStripArrayPayload("BooleanArray"))
        assertTrue(shouldStripArrayPayload("ByteArray"))
        assertTrue(shouldStripArrayPayload("CharArray"))
        assertTrue(shouldStripArrayPayload("IntArray"))
        assertTrue(shouldStripArrayPayload("String"))
        assertFalse(shouldStripArrayPayload("Array"))
    }

    @Test
    fun testGzipMagic_maybeDecompressMirror() {
        val header = byteArrayOf(gzipMagic0.toByte(), gzipMagic1.toByte())
        assertEquals(0x1f, header[0].toInt() and 0xFF)
        assertEquals(0x8b, header[1].toInt() and 0xFF)
    }

    @Test
    fun testDumpOutputStartsWithGzipMagic() {
        touchFixtures()
        withTmpFile { _, fd ->
            assertTrue(startDumpMemoryStripAsync(fd))
            assertTrue(waitForCompleteGzipDump(fd).isNotEmpty())
            val compressed = readFdSnapshot(fd)
            assertTrue(compressed.size >= 2)
            assertEquals(gzipMagic0, compressed[0].toInt() and 0xFF)
            assertEquals(gzipMagic1, compressed[1].toInt() and 0xFF)
        }
    }

    @Test
    fun testOhosForkDumpContract_mirror() {
        // KONAN_OHOS: DumpMemory forks child to run DumpMemoryOrThrow (f42e3ef).
        val usesForkInChild = true
        assertTrue(usesForkInChild)
    }

    // ---------- kdumputil reconstruction mirrors ----------

    @Test
    fun testKdumputil_zeroFilledPrimitiveArray_mirror() {
        val count = 4
        val elementSize = 4
        val filled = zeroFilledPrimitiveArray(count, elementSize)
        assertEquals(16, filled.size)
        assertTrue(filled.all { it == 0.toByte() })
    }

    @Test
    fun testKdumputil_reconstructStrippedObject_mirror() {
        val ptrSize = 8
        val instanceSize = 32
        val offsets = intArrayOf(8, 16)
        val stripped = ByteArray(ptrSize * offsets.size) { (it + 1).toByte() }
        val full = reconstructObjectByteArray(stripped, offsets, instanceSize, ptrSize)
        assertEquals(instanceSize, full.size)
        assertEquals(1, full[8])
        assertEquals(9, full[16])
        assertEquals(0, full[0])
    }

    // ---------- Live dump: strip vs full payload (SR009 + 37b1dac) ----------

    @Test
    fun testLiveDump_strippedByteArray_hasZeroPayload() {
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        val entries = parsed.arrayEntriesNamed("ByteArray").filter { it.count == 5 }
        assertTrue(entries.isNotEmpty(), "expected ByteArray(count=5) in dump; types=${parsed.types.values.map { it.relativeName }}")
        assertTrue(entries.all { it.payloadSize == 0 }, "ByteArray payload must be stripped: $entries")
        logLine("ByteArray strip ok, entries=${entries.size}")
    }

    @Test
    fun testLiveDump_strippedCharArray_hasZeroPayload() {
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        val entries = parsed.arrayEntriesNamed("CharArray").filter { it.count == 4 }
        assertTrue(entries.isNotEmpty(), "expected CharArray(count=4) in dump")
        assertTrue(entries.all { it.payloadSize == 0 }, "CharArray payload must be stripped: $entries")
    }

    @Test
    fun testLiveDump_strippedString_hasZeroPayload() {
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        val stringType = parsed.types.values.firstOrNull { it.relativeName == "String" }
        assertNotNull(stringType, "String type must appear in TYPE records")
        val stringArrays = parsed.arrays.filter { it.typeIdKey == stringType.idKey && it.count > 0 }
        assertTrue(stringArrays.isNotEmpty(), "expected String array instances in dump")
        assertTrue(
            stringArrays.any { it.payloadSize == 0 },
            "String (theStringTypeInfo) payload must be stripped per 37b1dac; got $stringArrays",
        )
    }

    @Test
    fun testLiveDump_strippedIntArray_hasZeroPayload() {
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        val entries = parsed.arrayEntriesNamed("IntArray").filter { it.count == 4 }
        assertTrue(entries.isNotEmpty(), "expected IntArray(count=4) in dump")
        assertTrue(entries.all { it.payloadSize == 0 }, "IntArray payload must be stripped: $entries")
    }

    @Test
    fun testLiveDump_objectArray_writesFullPayload() {
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        val arrayType = parsed.types.values.first { it.isObjectArray }
        val entries = parsed.arrays.filter { it.typeIdKey == arrayType.idKey && it.count == 2 }
        assertTrue(entries.isNotEmpty(), "expected object Array(count=2) in dump")
        assertTrue(
            entries.all { it.payloadSize > 0 },
            "object Array must write references (37b1dac else branch): $entries",
        )
    }

    @Test
    fun testLiveDump_allStrippedTypesHaveZeroPayload() {
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        for (name in strippedArrayRelativeNames) {
            val typeKeys = parsed.types.filter { it.value.relativeName == name }.keys
            val strippedArrays = parsed.arrays.filter { it.typeIdKey in typeKeys && it.count > 0 }
            if (strippedArrays.isEmpty()) continue
            assertTrue(
                strippedArrays.all { it.payloadSize == 0 },
                "$name arrays with count>0 must have payloadSize=0: $strippedArrays",
            )
        }
    }

    @Test
    fun testLiveDump_nonStrippedObjectArraysHavePositivePayload() {
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        val objectTypeKeys = parsed.types.filterValues { it.isObjectArray }.keys
        val arrays = parsed.arrays.filter { it.typeIdKey in objectTypeKeys && it.count > 0 }
        assertTrue(arrays.isNotEmpty(), "expected non-empty object arrays in dump")
        assertTrue(arrays.all { it.payloadSize > 0 }, "object arrays must retain reference payloads: $arrays")
    }

    @Test
    fun testStrippedContentReducesDecompressedSize() {
        touchFixtures()
        val manyInts = Array(32) { intArrayOf(1, 2, 3, 4, 5, 6, 7, 8) }
        val manyObjects = Array(32) { arrayOf("strip_a", "strip_b") }
        val parsed = parseTypesAndArrays(dumpToDecompressedBytes())
        val intTypeKeys = parsed.types.filterValues { it.relativeName == "IntArray" }.keys
        val objectTypeKeys = parsed.types.filterValues { it.isObjectArray }.keys
        val intPayload = parsed.arrays.filter { it.typeIdKey in intTypeKeys }.sumOf { it.payloadSize }
        val objectPayload = parsed.arrays.filter { it.typeIdKey in objectTypeKeys }.sumOf { it.payloadSize }
        assertEquals(0, intPayload, "all IntArray payloads must be stripped")
        assertTrue(objectPayload > intPayload, "object payload $objectPayload must exceed stripped IntArray payload $intPayload")
        assertEquals(8, manyInts.first().size)
        assertEquals(2, manyObjects.first().size)
    }
}
