// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 256 -> sentence 256
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 256 -> sentence 256
 *                inheritance, inheriting -> paragraph 256 -> sentence 256
 *                inheritance, overriding -> paragraph 256 -> sentence 256
 * NUMBER: 1
 * DESCRIPTION: precise types when a single override satisfies identically named members from two generic interfaces with the same type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface A<T> {
    fun f(): T
}

interface B<T> {
    fun f(): T
}

class C : A<Int>, B<Int> {
    override fun f(): Int = 1
}

fun case1() {
    val c = C()
    c checkType { check<C>() }
    checkSubtype<A<Int>>(c)
    checkSubtype<B<Int>>(c)
    c.f() checkType { check<Int>() }
    val asA: A<Int> = c
    asA.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface LeftText<T> {
    fun text(): T
}

interface RightText<T> {
    fun text(): T
}

class StringBoth : LeftText<String>, RightText<String> {
    override fun text(): String = "ok"
}

fun case2() {
    val s = StringBoth()
    checkSubtype<LeftText<String>>(s)
    checkSubtype<RightText<String>>(s)
    s.text() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface LeftVal<T> {
    val n: T
}

interface RightVal<T> {
    val n: T
}

class BoolBoth : LeftVal<Boolean>, RightVal<Boolean> {
    override val n: Boolean = true
}

fun case3() {
    val b = BoolBoth()
    checkSubtype<LeftVal<Boolean>>(b)
    checkSubtype<RightVal<Boolean>>(b)
    b.n checkType { check<Boolean>() }
}
