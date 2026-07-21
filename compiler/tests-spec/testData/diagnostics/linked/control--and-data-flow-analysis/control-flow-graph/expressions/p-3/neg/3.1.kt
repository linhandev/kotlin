// FIR_IDENTICAL
// DIAGNOSTICS: -IMPLICIT_CAST_TO_ANY -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: if used as expression must have both branches, including else-if chain without final else
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val flag = true
    val value = <!INVALID_IF_AS_EXPRESSION!>if<!> (flag) {
        "true"
    }
}

// TESTCASE NUMBER: 2
fun case_2() {
    val flag = true
    val value = <!INVALID_IF_AS_EXPRESSION!>if<!> (flag) "true"
}

// TESTCASE NUMBER: 3
fun case_3() {
    val first = true
    val second = false
    val value = if (first) {
        "first"
    } else <!INVALID_IF_AS_EXPRESSION!>if<!> (second) {
        "second"
    }
}
