// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: fun interface name must be a valid identifier; numeric literal is rejected
 */

// TESTCASE NUMBER: 1
fun interface <!SYNTAX!>1<!> {
    fun invoke(): Int
}
