// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 23 -> sentence 23
 *                expressions, additive-expressions -> paragraph 23 -> sentence 23
 *                declarations, declarations-with-type-parameters -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: extension operator plus on generic Box<Int> infers Box
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

operator fun Box<Int>.plus(other: Box<Int>) = Box(value + other.value)

fun case1() {
    checkSubtype<Box<Int>>(Box(1) + Box(2))
}
