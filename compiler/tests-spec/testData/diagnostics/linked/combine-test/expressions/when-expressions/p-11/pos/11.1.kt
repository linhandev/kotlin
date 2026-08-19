// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 11 -> sentence 11
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 11 -> sentence 11
 *                type-inference, smart-casts -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: when expression with sealed class subject and smart cast type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Shape {
    class Circle(val r: Int) : Shape()
    class Rect(val w: Int, val h: Int) : Shape()
}

fun case1() {
    val s: Shape = Shape.Circle(3)
    checkSubtype<Int>(when (s) {
        is Shape.Circle -> s.r
        is Shape.Rect -> s.w * s.h
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val s: Shape = Shape.Rect(2, 4)
    checkSubtype<Int>(when (s) {
        is Shape.Circle -> s.r
        is Shape.Rect -> s.w * s.h
    })
}
