// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: if/when expression with both branches present is valid CFG conditional expression
 */

// TESTCASE NUMBER: 1
fun case_1(): String {
    val flag = true
    return if (flag) "true" else "false"
}

// TESTCASE NUMBER: 2
fun case_2(): String {
    val flag = true
    return when {
        flag -> "yes"
        else -> "no"
    }
}
