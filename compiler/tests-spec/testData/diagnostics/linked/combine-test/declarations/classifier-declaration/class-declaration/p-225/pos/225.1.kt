// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 225 -> sentence 225
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 225 -> sentence 225
 *                inheritance, overriding -> paragraph 225 -> sentence 225
 * NUMBER: 1
 * DESCRIPTION: type inference when same-named val dual default getters are resolved via override + qualified super
 * HELPERS: checkType
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

fun case1() {
    val c = SumX()
    c checkType { check<SumX>() }
    checkSubtype<LeftX>(c)
    checkSubtype<RightX>(c)
    c.x checkType { check<Int>() }
    val asLeft: LeftX = c
    asLeft.x checkType { check<Int>() }
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

fun case2() {
    val c = ConcatLabel()
    c checkType { check<ConcatLabel>() }
    checkSubtype<LeftLabel>(c)
    checkSubtype<RightLabel>(c)
    c.label checkType { check<String>() }
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

fun case3() {
    val c = SumLong()
    c checkType { check<SumLong>() }
    checkSubtype<LeftLong>(c)
    checkSubtype<RightLong>(c)
    c.n checkType { check<Long>() }
}
