// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: vararg parameter default value must be an array type; non-array literal default is rejected
 */

// TESTCASE NUMBER: 1
fun scalarDefault(vararg xs: Int = <!INITIALIZER_TYPE_MISMATCH!>1<!>): Int = xs.sum()

// TESTCASE NUMBER: 2
fun wrongArrayType(vararg xs: Int = <!INITIALIZER_TYPE_MISMATCH!>doubleArrayOf(1.0)<!>): Int = xs.sum()
