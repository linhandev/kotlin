// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 150 -> sentence 150
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 150 -> sentence 150
 *                declarations, classifier-declaration, interface-declaration -> paragraph 150 -> sentence 150
 * NUMBER: 1
 * DESCRIPTION: class declaration supertype list may include one class and multiple interfaces
 */

// TESTCASE NUMBER: 1
interface A {
    fun a(): Int
}

interface B {
    fun b(): String
}

open class Base

class Impl : Base(), A, B {
    override fun a(): Int = 1
    override fun b(): String = "b"
}

// TESTCASE NUMBER: 2
interface Reader {
    fun read(): Int
}

interface Writer {
    fun write(v: Int): Int
}

open class Store(val seed: Int)

class FileStore(seed: Int) : Store(seed), Reader, Writer {
    override fun read(): Int = seed
    override fun write(v: Int): Int = seed + v
}

// TESTCASE NUMBER: 3
interface X {
    fun x(): Int
}

interface Y {
    fun y(): Int
}

interface Z {
    fun z(): Int
}

open class Root(val n: Int)

class Triple : Root(7), X, Y, Z {
    override fun x(): Int = n
    override fun y(): Int = n + 1
    override fun z(): Int = n + 2
}

fun viaImpl(): Boolean {
    val i = Impl()
    return i is Base && i is A && i is B && i.a() == 1 && i.b() == "b"
}

fun viaFileStore(): Pair<Int, Int> {
    val f = FileStore(10)
    return f.read() to f.write(5)
}

fun viaTriple(): List<Int> {
    val t = Triple()
    return listOf(t.x(), t.y(), t.z())
}

fun box(): String {
    if (!viaImpl()) return "NOK: impl"
    if (viaFileStore() != (10 to 15)) return "NOK: file-store"
    if (viaTriple() != listOf(7, 8, 9)) return "NOK: triple"
    if (FileStore(2).write(3) != 5) return "NOK: write"
    return "OK"
}
