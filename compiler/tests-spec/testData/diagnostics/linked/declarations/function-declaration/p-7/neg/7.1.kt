// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: default parameter value cannot reference a parameter declared later in the parameter list
 */

// TESTCASE NUMBER: 1
fun circular(x: Int = <!UNINITIALIZED_PARAMETER!>y<!>, y: Int = x): Int = x + y

// TESTCASE NUMBER: 2
fun forwardRef(a: Int = <!TYPE_MISMATCH, UNINITIALIZED_PARAMETER!>b<!>, b: String = "ok") {}
