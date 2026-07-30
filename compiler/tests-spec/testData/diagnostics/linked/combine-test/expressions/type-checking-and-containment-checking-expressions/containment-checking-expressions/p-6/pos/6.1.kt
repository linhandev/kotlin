// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 *                declarations, function-declaration, extension-function-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: generic extension contains function is resolved by in operator and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val items: Set<T>)
operator fun <T> Box<T>.contains(x: T): Boolean = x in items

fun case1() {
    checkSubtype<Boolean>(2 in Box(setOf(1, 2, 3)))
    checkSubtype<Boolean>("a" in Box(setOf("a", "b")))
}
