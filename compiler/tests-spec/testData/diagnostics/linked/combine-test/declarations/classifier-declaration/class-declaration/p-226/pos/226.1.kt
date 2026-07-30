// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 226 -> sentence 226
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 226 -> sentence 226
 *                inheritance, overriding -> paragraph 226 -> sentence 226
 * NUMBER: 1
 * DESCRIPTION: type inference for ordinary and sealed-leaf multi-interface override resolution when unrelated enum/sealed classifiers coexist
 * HELPERS: checkType
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

fun case1() {
    val c = R()
    c checkType { check<R>() }
    checkSubtype<P>(c)
    checkSubtype<Q>(c)
    c.tag() checkType { check<String>() }
    UnrelatedColor.RED checkType { check<UnrelatedColor>() }
    checkSubtype<UnrelatedShape>(UnrelatedCircle())
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

fun case2() {
    val c = OrdinarySum()
    c checkType { check<OrdinarySum>() }
    checkSubtype<LeftNum>(c)
    checkSubtype<RightNum>(c)
    c.n() checkType { check<Int>() }
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

fun case3() {
    val c = MarkedLeaf()
    c checkType { check<MarkedLeaf>() }
    checkSubtype<Marked>(c)
    checkSubtype<LeftMark>(c)
    checkSubtype<RightMark>(c)
    c.mark checkType { check<String>() }
}
