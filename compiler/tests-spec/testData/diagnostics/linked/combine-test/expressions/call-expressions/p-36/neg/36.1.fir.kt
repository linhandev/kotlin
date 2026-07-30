// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 36 -> sentence 36
 *                type-inference, introduction-1 -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: type argument cannot be inferred without sufficient context
 */

// TESTCASE NUMBER: 1
fun <T> empty(): List<T> = emptyList()

fun test() = <!CANNOT_INFER_PARAMETER_TYPE!>empty<!>()
