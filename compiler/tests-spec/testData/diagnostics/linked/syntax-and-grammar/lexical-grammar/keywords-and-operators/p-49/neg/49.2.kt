// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 49 -> sentence 49
 * NUMBER: 2
 * DESCRIPTION: Space in EQEQEQ token as = == breaks EQEQEQ lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val a = intArrayOf(1)
    val b = a
    return if (<!VAL_REASSIGNMENT!>a<!> = <!SYNTAX!>==<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>b<!><!SYNTAX!><!>) "OK" else "NOK"
}
