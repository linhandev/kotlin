// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, return-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: return in non-inline lambda reports RETURN_NOT_ALLOWED
 */

fun accept(block: () -> Unit) {
    block()
}

// TESTCASE NUMBER: 1
fun case1(): String {
    accept {
        <!RETURN_NOT_ALLOWED!>return<!> "OK"
    }
    return "NOK"
}
