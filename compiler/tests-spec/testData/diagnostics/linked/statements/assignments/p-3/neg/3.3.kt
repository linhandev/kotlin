// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: Safe assignment with incompatible right-hand side type reports CONSTANT_EXPECTED_TYPE_MISMATCH
 */

class Holder {
    var n = 0
}

// TESTCASE NUMBER: 1
fun case1() {
    val h: Holder? = Holder()
    h?.n = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1L<!>
}
