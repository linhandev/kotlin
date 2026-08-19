// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 19 -> sentence 19
 *                expressions, when-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: when expression with Any subject does not require sealed class exhaustiveness when else branch is present
 */

// TESTCASE NUMBER: 1
sealed class Shape {
    class Circle(val r: Int) : Shape()
    class Rect(val w: Int, val h: Int) : Shape()
}

fun test(x: Any): Int = when (x) {
    is Shape.Circle -> x.r
    is Shape.Rect -> x.w * x.h
    else -> -1
}

fun box(): String {
    if (test(Shape.Circle(3)) != 3) return "NOK"
    if (test(Shape.Rect(2, 4)) != 8) return "NOK"
    if (test("other") != -1) return "NOK"
    return "OK"
}
