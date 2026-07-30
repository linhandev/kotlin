// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 45 -> sentence 45
 *                type-inference, introduction-1 -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: class type parameter inferred from constructor arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T)

fun case_1() {
    val b = Box(1)
    checkSubtype<Box<Int>>(b)
    val x: Int = b.v
    checkSubtype<Int>(x)
}
