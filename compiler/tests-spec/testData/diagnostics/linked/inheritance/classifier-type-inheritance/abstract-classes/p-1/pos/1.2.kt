// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Triangle511 implements abstract name sides and area() from Shape511
 */

abstract class Shape511 {
    abstract val name: String
    abstract var sides: Int
    abstract fun area(): Double
}

class Triangle511 : Shape511() {
    override val name: String = "triangle"
    override var sides: Int = 3
    override fun area(): Double = 1.5
}

// TESTCASE NUMBER: 1
fun case1(t: Triangle511): Double {
    t.sides = 4
    return t.area()
}
