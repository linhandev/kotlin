// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 104 -> sentence 104
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 104 -> sentence 104
 * NUMBER: 1
 * DESCRIPTION: class without invoke cannot be used with call convention
 */

// TESTCASE NUMBER: 1
class NotCallable

fun test() = <!FUNCTION_EXPECTED!>NotCallable()<!>()
