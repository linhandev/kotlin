// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 240 -> sentence 240
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 240 -> sentence 240
 *                inheritance, inheriting -> paragraph 240 -> sentence 240
 * NUMBER: 1
 * DESCRIPTION: precise types when a class implements an interface type parameter constrained by Number and uses bound members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface NumBox<T : Number> {
    fun value(): T
    fun asDouble(): Double = value().toDouble()
}

class IntBox : NumBox<Int> {
    override fun value(): Int = 7
}

fun case1() {
    val b = IntBox()
    b checkType { check<IntBox>() }
    checkSubtype<NumBox<Int>>(b)
    b.value() checkType { check<Int>() }
    b.asDouble() checkType { check<Double>() }
}

// TESTCASE NUMBER: 2
interface Scaled<T : Number> {
    fun raw(): T
    fun scaled(factor: Double): Double = raw().toDouble() * factor
}

class LongScaled : Scaled<Long> {
    override fun raw(): Long = 3L
}

fun case2() {
    val s = LongScaled()
    checkSubtype<Scaled<Long>>(s)
    s.raw() checkType { check<Long>() }
    s.scaled(2.0) checkType { check<Double>() }
}

// TESTCASE NUMBER: 3
interface Measurable<T : Number> {
    val amount: T
}

class DoubleMeasure(override val amount: Double) : Measurable<Double>

fun case3() {
    val m = DoubleMeasure(1.5)
    checkSubtype<Measurable<Double>>(m)
    m.amount checkType { check<Double>() }
}
