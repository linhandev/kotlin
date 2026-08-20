// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 176 -> sentence 176
 * PRIMARY LINKS: inheritance, overriding -> paragraph 176 -> sentence 176
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 176 -> sentence 176
 *                inheritance, inheriting -> paragraph 176 -> sentence 176
 * NUMBER: 1
 * DESCRIPTION: type inference for overriding an open member that changes the implementation in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Identity {
    open fun compute(n: Int): Int = n
}

class Square : Identity() {
    override fun compute(n: Int): Int = n * n
}

fun case1() {
    val sq = Square()
    sq checkType { check<Square>() }
    checkSubtype<Identity>(sq)
    sq.compute(3) checkType { check<Int>() }

    val viaBase: Identity = sq
    viaBase.compute(3) checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Counter {
    open val step: Int get() = 1
    fun advance(from: Int): Int = from + step
}

class DoubleStep : Counter() {
    override val step: Int get() = 2
}

fun case2() {
    val d = DoubleStep()
    d checkType { check<DoubleStep>() }
    checkSubtype<Counter>(d)
    d.step checkType { check<Int>() }
    d.advance(10) checkType { check<Int>() }
}
