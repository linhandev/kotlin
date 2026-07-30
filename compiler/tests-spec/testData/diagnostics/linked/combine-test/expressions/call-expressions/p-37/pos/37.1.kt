// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 37 -> sentence 37
 *                type-inference, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: explicit type argument supplements uninferrable type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> empty(): List<T> = emptyList()

fun case_1() {
    checkSubtype<List<Int>>(empty<Int>())
    checkSubtype<List<String>>(empty<String>())
}
