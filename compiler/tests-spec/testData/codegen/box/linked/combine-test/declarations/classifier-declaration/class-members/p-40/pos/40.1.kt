// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 40 -> sentence 40
 *                expressions, additive-expressions -> paragraph 40 -> sentence 40
 *                type-inference, smart-casts -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: extension plus on Shape uses smart cast of operands inside operator body
 */

// TESTCASE NUMBER: 1
open class Shape
class Circle(val r: Int = 1) : Shape()
class Square : Shape()

operator fun Shape.plus(other: Shape): Shape =
    if (this is Circle && other is Circle) Circle(r + other.r) else Circle(0)

fun test(s: Shape): Shape = s + s

fun box(): String {
    val bothCircles = test(Circle(2))
    if (bothCircles !is Circle || bothCircles.r != 4) return "NOK: circle"
    val mixed = Circle(2) + Square()
    if (mixed !is Circle || mixed.r != 0) return "NOK: mixed"
    return "OK"
}
