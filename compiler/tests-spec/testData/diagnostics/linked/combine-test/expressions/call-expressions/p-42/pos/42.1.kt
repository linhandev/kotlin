// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 42 -> sentence 42
 *                type-inference, introduction-1 -> paragraph 42 -> sentence 42
 *                expressions, function-literals, lambda-literals -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: higher-order stdlib call infers type arguments from lambda argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val xs = listOf("a", "bc")
    val result = xs.map { it.length }
    checkSubtype<List<Int>>(result)
}
