// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 24 -> sentence 24
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: trailing lambda cannot follow call whose last parameter is not a function type
 */

// TESTCASE NUMBER: 1
fun greet(name: String): String = "hi $name"

fun test() = greet <!TYPE_MISMATCH!>{ }<!>
