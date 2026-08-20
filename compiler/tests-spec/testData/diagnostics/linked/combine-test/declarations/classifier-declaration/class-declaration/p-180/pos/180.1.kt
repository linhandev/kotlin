// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 180 -> sentence 180
 * PRIMARY LINKS: inheritance, overriding -> paragraph 180 -> sentence 180
 *                declarations, property-declaration -> paragraph 180 -> sentence 180
 *                inheritance, inheriting -> paragraph 180 -> sentence 180
 * NUMBER: 1
 * DESCRIPTION: type inference for override val replacing an open val in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Meter {
    open val reading: Int = 1
}

class ScaledMeter : Meter() {
    override val reading: Int = 2
}

fun case1() {
    val scaled = ScaledMeter()
    scaled checkType { check<ScaledMeter>() }
    checkSubtype<Meter>(scaled)
    scaled.reading checkType { check<Int>() }

    val asBase: Meter = scaled
    asBase.reading checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Score {
    open val points: Int get() = 10
}

class BonusScore : Score() {
    override val points: Int get() = 25
}

fun case2() {
    val bonus = BonusScore()
    bonus checkType { check<BonusScore>() }
    checkSubtype<Score>(bonus)
    bonus.points checkType { check<Int>() }
}
