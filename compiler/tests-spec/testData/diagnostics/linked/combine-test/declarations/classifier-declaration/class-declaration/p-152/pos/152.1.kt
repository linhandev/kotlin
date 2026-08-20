// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 152 -> sentence 152
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 152 -> sentence 152
 *                inheritance, inheriting -> paragraph 152 -> sentence 152
 *                declarations, classifier-declaration, interface-declaration -> paragraph 152 -> sentence 152
 * NUMBER: 1
 * DESCRIPTION: interfaces in the supertype list do not participate in constructor delegation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I

open class Base(val x: Int)

class Impl(x: Int) : Base(x), I

fun case1() {
    val i = Impl(3)
    i checkType { check<Impl>() }
    checkSubtype<Base>(i)
    checkSubtype<I>(i)
    i.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface Tagged {
    fun tag(): String
}

open class Store(val seed: Int)

class TaggedStore(seed: Int) : Store(seed), Tagged {
    override fun tag(): String = "s$seed"
}

fun case2() {
    val t = TaggedStore(5)
    t checkType { check<TaggedStore>() }
    checkSubtype<Store>(t)
    checkSubtype<Tagged>(t)
    t.seed checkType { check<Int>() }
    t.tag() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface A {
    fun a(): Int
}

interface B {
    fun b(): Int
}

open class Root(val n: Int)

class Multi(n: Int) : Root(n), A, B {
    override fun a(): Int = n
    override fun b(): Int = n * 2
}

fun case3() {
    val m = Multi(4)
    m checkType { check<Multi>() }
    checkSubtype<Root>(m)
    checkSubtype<A>(m)
    checkSubtype<B>(m)
    m.n checkType { check<Int>() }
}
