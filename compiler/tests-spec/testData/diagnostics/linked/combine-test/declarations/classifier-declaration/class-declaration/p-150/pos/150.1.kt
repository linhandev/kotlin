// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 150 -> sentence 150
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 150 -> sentence 150
 *                declarations, classifier-declaration, interface-declaration -> paragraph 150 -> sentence 150
 * NUMBER: 1
 * DESCRIPTION: class declaration supertype list may include one class and multiple interfaces type inference
 * HELPERS: checkType
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

fun case1() {
    val i = Impl()
    i checkType { check<Impl>() }
    checkSubtype<Base>(i)
    checkSubtype<A>(i)
    checkSubtype<B>(i)
    i.a() checkType { check<Int>() }
    i.b() checkType { check<String>() }
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

fun case2() {
    val f = FileStore(10)
    f checkType { check<FileStore>() }
    checkSubtype<Store>(f)
    checkSubtype<Reader>(f)
    checkSubtype<Writer>(f)
    f.seed checkType { check<Int>() }
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

fun case3() {
    val t = Triple()
    t checkType { check<Triple>() }
    checkSubtype<Root>(t)
    checkSubtype<X>(t)
    checkSubtype<Y>(t)
    checkSubtype<Z>(t)
}
