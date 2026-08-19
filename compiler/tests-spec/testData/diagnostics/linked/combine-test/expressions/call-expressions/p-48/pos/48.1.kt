// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 48 -> sentence 48
 *                type-inference, introduction-1 -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: chained generic calls infer type arguments step by step
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val xs = listOf("hello", "world", "")
    val result = xs.map { it.uppercase() }.filter { it.isNotEmpty() }
    checkSubtype<List<String>>(result)
}
