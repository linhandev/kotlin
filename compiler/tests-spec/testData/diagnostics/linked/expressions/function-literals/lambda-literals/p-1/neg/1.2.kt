// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: non-labeled return in non-inline lambda is not allowed
 */

fun <T> runBlock(f: () -> T): T = f()

// TESTCASE NUMBER: 1
fun case1() {
    runBlock { <!RETURN_NOT_ALLOWED!>return<!> }
}
