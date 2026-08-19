// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 240 -> sentence 240
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 240 -> sentence 240
 *                inheritance, inheriting -> paragraph 240 -> sentence 240
 * NUMBER: 1
 * DESCRIPTION: a class may implement an interface whose type parameter has upper bound Number, fixing T to a Number subtype and using bound members; contrasts with class-only bounds in p-60 and the next-point bound violation
 */

// TESTCASE NUMBER: 1
interface NumBox<T : Number> {
    fun value(): T
    fun asDouble(): Double = value().toDouble()
}

class IntBox(private val v: Int) : NumBox<Int> {
    override fun value(): Int = v
}

// TESTCASE NUMBER: 2
interface Scaled<T : Number> {
    fun raw(): T
    fun scaled(factor: Double): Double = raw().toDouble() * factor
}

class LongScaled(private val v: Long) : Scaled<Long> {
    override fun raw(): Long = v
}

// TESTCASE NUMBER: 3
interface Measurable<T : Number> {
    val amount: T
}

class DoubleMeasure(override val amount: Double) : Measurable<Double>

fun box(): String {
    if (IntBox(7).value() != 7) return "NOK: int-value"
    if (IntBox(7).asDouble() != 7.0) return "NOK: int-double"
    val asNum: NumBox<Int> = IntBox(7)
    if (asNum.asDouble() != 7.0) return "NOK: via-numbox"

    if (LongScaled(3L).raw() != 3L) return "NOK: long-raw"
    if (LongScaled(3L).scaled(2.0) != 6.0) return "NOK: long-scaled"

    if (DoubleMeasure(1.5).amount != 1.5) return "NOK: double-amount"
    val asMeasurable: Measurable<Double> = DoubleMeasure(1.5)
    if (asMeasurable.amount != 1.5) return "NOK: via-measurable"
    return "OK"
}
