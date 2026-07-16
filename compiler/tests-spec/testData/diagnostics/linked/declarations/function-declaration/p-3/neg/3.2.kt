// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: JVM-erased signature conflict between overloads
 */

// TESTCASE NUMBER: 1
<!CONFLICTING_OVERLOADS!>fun clash(x: Int): Int<!> = x
<!CONFLICTING_OVERLOADS!>fun clash(x: Int): String<!> = x.toString()
