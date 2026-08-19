// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: non-inline higher-order forbids bare return in lambda
 */

// TESTCASE NUMBER: 1
fun invokeRun(block: () -> Unit): Unit = block()

fun case_1() {
    invokeRun { <!RETURN_NOT_ALLOWED!>return<!> }
}
