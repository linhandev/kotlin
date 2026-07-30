// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 214 -> sentence 214
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 214 -> sentence 214
 *                inheritance, overriding -> paragraph 214 -> sentence 214
 * NUMBER: 1
 * DESCRIPTION: type inference when a class declaration implements two interfaces whose same-named members differ by parameter list (overloads, not override conflict)
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface IntArg {
    fun f(x: Int): Int
}

interface StringArg {
    fun f(s: String): String
}

class DualOverload : IntArg, StringArg {
    override fun f(x: Int): Int = x
    override fun f(s: String): String = s
}

fun case1() {
    val c = DualOverload()
    c checkType { check<DualOverload>() }
    checkSubtype<IntArg>(c)
    checkSubtype<StringArg>(c)
    c.f(1) checkType { check<Int>() }
    c.f("hi") checkType { check<String>() }
}

// TESTCASE NUMBER: 2
interface BoolArg {
    fun g(b: Boolean): Boolean
}

interface LongArg {
    fun g(n: Long): Long
}

class DualPrimOverload : BoolArg, LongArg {
    override fun g(b: Boolean): Boolean = !b
    override fun g(n: Long): Long = n + 1L
}

fun case2() {
    val c = DualPrimOverload()
    c checkType { check<DualPrimOverload>() }
    checkSubtype<BoolArg>(c)
    checkSubtype<LongArg>(c)
    c.g(true) checkType { check<Boolean>() }
    c.g(1L) checkType { check<Long>() }
}

// TESTCASE NUMBER: 3
interface LeftPair {
    fun h(a: Int, b: Int): Int
}

interface RightSingle {
    fun h(a: Int): Int
}

class ArityOverload : LeftPair, RightSingle {
    override fun h(a: Int, b: Int): Int = a + b
    override fun h(a: Int): Int = a * 2
}

fun case3() {
    val c = ArityOverload()
    c checkType { check<ArityOverload>() }
    checkSubtype<LeftPair>(c)
    checkSubtype<RightSingle>(c)
    c.h(1, 2) checkType { check<Int>() }
    c.h(3) checkType { check<Int>() }
}
