// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 12 -> sentence 12
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: when expression with sealed class subject missing direct subclass and without else is not exhaustive
 */

// TESTCASE NUMBER: 1
sealed class Shape {
    class Circle(val r: Int) : Shape()
    class Rect(val w: Int, val h: Int) : Shape()
    object Unknown : Shape()
}

fun test(s: Shape): Int = <!NO_ELSE_IN_WHEN!>when<!>(s) {
    is Shape.Circle -> s.r
    is Shape.Rect -> s.w * s.h
}
