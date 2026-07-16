// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: local function is not visible outside the function where it is declared
 */

// TESTCASE NUMBER: 1
fun outerWithLocal() {
    fun inner() {}
}

fun callLocalFromOutside() {
    <!UNRESOLVED_REFERENCE!>inner<!>()
}
