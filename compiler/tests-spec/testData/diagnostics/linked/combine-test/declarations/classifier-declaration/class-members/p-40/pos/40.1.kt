// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST -DEBUG_INFO_IMPLICIT_RECEIVER_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 40 -> sentence 40
 *                expressions, additive-expressions -> paragraph 40 -> sentence 40
 *                type-inference, smart-casts -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: extension plus with smart cast of Shape operands infers Shape
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Shape
class Circle(val r: Int = 1) : Shape()
class Square : Shape()

operator fun Shape.plus(other: Shape): Shape =
    if (this is Circle && other is Circle) Circle(r + other.r) else Circle(0)

fun case1(s: Shape) {
    checkSubtype<Shape>(s + s)
}
