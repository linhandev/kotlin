// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 19 -> sentence 19
 *                expressions, when-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: when expression with Any subject does not require sealed class exhaustiveness when else branch is present type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Shape {
    class Circle(val r: Int) : Shape()
    class Rect(val w: Int, val h: Int) : Shape()
}

fun case1() {
    val x: Any = Shape.Circle(3)
    checkSubtype<Int>(when (x) {
        is Shape.Circle -> x.r
        is Shape.Rect -> x.w * x.h
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = "other"
    checkSubtype<Int>(when (x) {
        is Shape.Circle -> x.r
        is Shape.Rect -> x.w * x.h
        else -> -1
    })
}
