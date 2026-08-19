// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 13 -> sentence 13
 *                expressions, when-expressions -> paragraph 13 -> sentence 13
 *                type-inference, smart-casts -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: when expression with sealed class subject and else branch covering unlisted direct subclass
 */

// TESTCASE NUMBER: 1
sealed class Shape {
    class Circle(val r: Int) : Shape()
    class Rect(val w: Int, val h: Int) : Shape()
    object Unknown : Shape()
}

fun test(s: Shape): Int = when (s) {
    is Shape.Circle -> s.r
    is Shape.Rect -> s.w * s.h
    else -> -1
}

fun box(): String {
    if (test(Shape.Circle(3)) != 3) return "NOK"
    if (test(Shape.Rect(2, 4)) != 8) return "NOK"
    if (test(Shape.Unknown) != -1) return "NOK"
    return "OK"
}
