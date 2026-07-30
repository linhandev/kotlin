// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, function-declaration, extension-function-declaration -> paragraph 10 -> sentence 10
 *                type-system, type-kinds, type-parameters -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to generic extension contains with type argument inference and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val values: Set<T>)

operator fun <T> Box<T>.contains(x: T): Boolean = x in values

fun case1() {
    checkSubtype<Boolean>(2 in Box(setOf(1, 2, 3)))
}
