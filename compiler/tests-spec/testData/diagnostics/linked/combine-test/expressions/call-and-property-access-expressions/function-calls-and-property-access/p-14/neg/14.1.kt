// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 14 -> sentence 14
 *                type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: explicit lambda parameter type incompatible with expected function type
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Any>): List<String> = xs.map { <!EXPECTED_PARAMETER_TYPE_MISMATCH!>s: String<!> -> s.uppercase() }
