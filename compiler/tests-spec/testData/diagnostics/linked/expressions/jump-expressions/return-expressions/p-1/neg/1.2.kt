// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, return-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: return@function in non-inline lambda referring to outer function reports RETURN_NOT_ALLOWED
 */

// TESTCASE NUMBER: 1
fun invoke(block: () -> String): String = block()

fun case1(): String {
    invoke {
        <!RETURN_NOT_ALLOWED!>return@case1<!> "OK"
    }
    return "NOK"
}
