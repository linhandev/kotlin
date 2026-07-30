// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 225 -> sentence 225
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 225 -> sentence 225
 *                inheritance, overriding -> paragraph 225 -> sentence 225
 * NUMBER: 1
 * DESCRIPTION: same-named val dual default getters from two interfaces must be resolved by override + qualified super; focuses on property getters (sum/concat), contrasting p-207 fun-primary mix, p-216 abstract vals without defaults, and p-223 constant override without super
 */

// TESTCASE NUMBER: 1
interface LeftX {
    val x: Int get() = 1
}

interface RightX {
    val x: Int get() = 2
}

class SumX : LeftX, RightX {
    override val x: Int get() = super<LeftX>.x + super<RightX>.x
}

// TESTCASE NUMBER: 2
interface LeftLabel {
    val label: String get() = "a"
}

interface RightLabel {
    val label: String get() = "b"
}

class ConcatLabel : LeftLabel, RightLabel {
    override val label: String get() = super<LeftLabel>.label + super<RightLabel>.label
}

// TESTCASE NUMBER: 3
interface LeftLong {
    val n: Long get() = 10L
}

interface RightLong {
    val n: Long get() = 20L
}

class SumLong : LeftLong, RightLong {
    override val n: Long get() = super<LeftLong>.n + super<RightLong>.n
}

fun box(): String {
    if (SumX().x != 3) return "NOK: sum-x"
    val asLeftX: LeftX = SumX()
    if (asLeftX.x != 3) return "NOK: via-left-x"
    val asRightX: RightX = SumX()
    if (asRightX.x != 3) return "NOK: via-right-x"
    if (object : LeftX {}.x != 1) return "NOK: left-x-alone"
    if (object : RightX {}.x != 2) return "NOK: right-x-alone"

    if (ConcatLabel().label != "ab") return "NOK: concat-label"
    val asLeftLabel: LeftLabel = ConcatLabel()
    if (asLeftLabel.label != "ab") return "NOK: via-left-label"
    val asRightLabel: RightLabel = ConcatLabel()
    if (asRightLabel.label != "ab") return "NOK: via-right-label"

    if (SumLong().n != 30L) return "NOK: sum-long"
    val asLeftLong: LeftLong = SumLong()
    if (asLeftLong.n != 30L) return "NOK: via-left-long"
    val asRightLong: RightLong = SumLong()
    if (asRightLong.n != 30L) return "NOK: via-right-long"
    return "OK"
}
