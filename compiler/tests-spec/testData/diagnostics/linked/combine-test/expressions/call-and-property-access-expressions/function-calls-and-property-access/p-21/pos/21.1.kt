// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 21 -> sentence 21
 *                type-system, nullable-types -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: safe call with trailing lambda infers String result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: String?) {
    checkSubtype<String>(x?.let { it.uppercase() } ?: "none")
}
