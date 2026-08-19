// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 11 -> sentence 11
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 11 -> sentence 11
 *                type-inference, smart-casts -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: when expression with sealed class subject is exhaustive when all direct subclasses are covered with smart cast
 */

// TESTCASE NUMBER: 1
sealed class Shape {
    class Circle(val r: Int) : Shape()
    class Rect(val w: Int, val h: Int) : Shape()
}

fun test(s: Shape): Int = when (s) {
    is Shape.Circle -> s.r
    is Shape.Rect -> s.w * s.h
}

fun box(): String {
    if (test(Shape.Circle(3)) != 3) return "NOK"
    if (test(Shape.Rect(2, 4)) != 8) return "NOK"
    return "OK"
}
