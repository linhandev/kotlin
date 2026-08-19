// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 2 -> sentence 2
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: trailing lambda binds only to the last parameter, earlier function-type parameters must be passed inside parentheses
 */

// TESTCASE NUMBER: 1
fun pair(first: () -> Unit, second: () -> Unit) {}

fun test() = <!NO_VALUE_FOR_PARAMETER!>pair<!> { }
