// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: catch parameter cannot be declared with val or var
 */

// TESTCASE NUMBER: 1
fun case_1() {
    try {
    } catch (<!VAL_OR_VAR_ON_CATCH_PARAMETER!>val<!> e: Exception) {
    }
}

// TESTCASE NUMBER: 2
fun case_2() {
    try {
    } catch (<!VAL_OR_VAR_ON_CATCH_PARAMETER!>var<!> e: Exception) {
    }
}
