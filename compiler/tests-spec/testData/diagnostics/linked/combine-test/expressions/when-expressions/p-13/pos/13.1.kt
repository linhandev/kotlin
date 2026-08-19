// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 13 -> sentence 13
 *                expressions, when-expressions -> paragraph 13 -> sentence 13
 *                type-inference, smart-casts -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: when expression with sealed class subject and else branch covering unlisted direct subclass type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Shape {
    class Circle(val r: Int) : Shape()
    class Rect(val w: Int, val h: Int) : Shape()
    object Unknown : Shape()
}

fun case1() {
    val s: Shape = Shape.Circle(3)
    checkSubtype<Int>(when (s) {
        is Shape.Circle -> s.r
        is Shape.Rect -> s.w * s.h
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val s: Shape = Shape.Unknown
    checkSubtype<Int>(when (s) {
        is Shape.Circle -> s.r
        is Shape.Rect -> s.w * s.h
        else -> -1
    })
}
