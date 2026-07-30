// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 25 -> sentence 25
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: non-function property access cannot be followed by trailing lambda
 */

// TESTCASE NUMBER: 1
data class Config(val name: String)

fun test(c: Config) = c.<!FUNCTION_EXPECTED!>name<!>{ }
