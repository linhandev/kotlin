// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 180 -> sentence 180
 * PRIMARY LINKS: inheritance, overriding -> paragraph 180 -> sentence 180
 *                declarations, property-declaration -> paragraph 180 -> sentence 180
 *                inheritance, inheriting -> paragraph 180 -> sentence 180
 * NUMBER: 1
 * DESCRIPTION: override val replaces an open val so the same property name yields different values for base vs subclass in a class declaration
 */

// TESTCASE NUMBER: 1
open class Meter {
    open val reading: Int = 1
}

class ScaledMeter : Meter() {
    override val reading: Int = 2
}

// TESTCASE NUMBER: 2
open class Named {
    open val title: String = "base"
    fun banner(): String = "|$title|"
}

class LoudNamed : Named() {
    override val title: String = "loud"
}

// TESTCASE NUMBER: 3
open class Score {
    open val points: Int get() = 10
}

class BonusScore : Score() {
    override val points: Int get() = 25
}

fun box(): String {
    val meter = Meter()
    val scaled = ScaledMeter()
    if (meter.reading != 1) return "NOK: meter"
    if (scaled.reading != 2) return "NOK: scaled"
    if (meter.reading == scaled.reading) return "NOK: reading-not-replaced"

    val asBase: Meter = scaled
    if (asBase.reading != 2) return "NOK: base-ref-reading"

    val named = Named()
    val loud = LoudNamed()
    if (named.banner() != "|base|") return "NOK: named-banner"
    if (loud.banner() != "|loud|") return "NOK: loud-banner"
    if ((loud as Named).banner() != "|loud|") return "NOK: named-ref-banner"

    if (Score().points != 10) return "NOK: score"
    if (BonusScore().points != 25) return "NOK: bonus"
    if ((BonusScore() as Score).points != 25) return "NOK: score-ref"
    return "OK"
}
