// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 226 -> sentence 226
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 226 -> sentence 226
 *                inheritance, overriding -> paragraph 226 -> sentence 226
 * NUMBER: 1
 * DESCRIPTION: ordinary multi-interface default conflicts still resolve via class override + qualified super even when unrelated enum/sealed classifiers coexist; contrasts with p-207 general dual-default mix and with p-225 val-getter-focused sum
 */

enum class UnrelatedColor { RED, GREEN }

sealed class UnrelatedShape

class UnrelatedCircle : UnrelatedShape()

// TESTCASE NUMBER: 1
interface P {
    fun tag(): String = "p"
}

interface Q {
    fun tag(): String = "q"
}

class R : P, Q {
    override fun tag(): String = super<P>.tag() + super<Q>.tag()
}

// TESTCASE NUMBER: 2
interface LeftNum {
    fun n(): Int = 1
}

interface RightNum {
    fun n(): Int = 2
}

class OrdinarySum : LeftNum, RightNum {
    override fun n(): Int = super<LeftNum>.n() + super<RightNum>.n()
}

// TESTCASE NUMBER: 3
interface LeftMark {
    val mark: String get() = "x"
}

interface RightMark {
    val mark: String get() = "y"
}

sealed class Marked

class MarkedLeaf : Marked(), LeftMark, RightMark {
    override val mark: String get() = super<LeftMark>.mark + super<RightMark>.mark
}

fun box(): String {
    if (UnrelatedColor.RED.name != "RED") return "NOK: unrelated-enum"
    if (UnrelatedCircle() !is UnrelatedShape) return "NOK: unrelated-sealed"

    if (R().tag() != "pq") return "NOK: ordinary-pq"
    val asP: P = R()
    if (asP.tag() != "pq") return "NOK: via-p"
    val asQ: Q = R()
    if (asQ.tag() != "pq") return "NOK: via-q"
    if (object : P {}.tag() != "p") return "NOK: p-alone"
    if (object : Q {}.tag() != "q") return "NOK: q-alone"

    if (OrdinarySum().n() != 3) return "NOK: ordinary-sum"
    val asLeftNum: LeftNum = OrdinarySum()
    if (asLeftNum.n() != 3) return "NOK: via-left-num"
    val asRightNum: RightNum = OrdinarySum()
    if (asRightNum.n() != 3) return "NOK: via-right-num"

    if (MarkedLeaf().mark != "xy") return "NOK: sealed-leaf"
    val asLeftMark: LeftMark = MarkedLeaf()
    if (asLeftMark.mark != "xy") return "NOK: via-left-mark"
    val asRightMark: RightMark = MarkedLeaf()
    if (asRightMark.mark != "xy") return "NOK: via-right-mark"
    val asMarked: Marked = MarkedLeaf()
    if ((asMarked as MarkedLeaf).mark != "xy") return "NOK: via-sealed"
    return "OK"
}
