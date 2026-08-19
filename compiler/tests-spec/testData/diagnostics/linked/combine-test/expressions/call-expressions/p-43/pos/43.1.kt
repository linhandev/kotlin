// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 43 -> sentence 43
 *                type-inference, introduction-1 -> paragraph 43 -> sentence 43
 *                type-system, upper-and-lower-bounds -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: type parameter with upper bound can still be inferred from value arguments at call site
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T : Comparable<T>> max(a: T, b: T): T = if (a >= b) a else b

fun case_1() {
    val result = max(1, 2)
    checkSubtype<Int>(result)
}

fun case_2() {
    val result = max("a", "b")
    checkSubtype<String>(result)
}
