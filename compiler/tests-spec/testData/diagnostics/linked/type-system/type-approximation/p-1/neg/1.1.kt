// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-approximation -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Approximated LUB cannot be used as unrelated branch type
 */
// TESTCASE NUMBER: 1
fun case_1() {
    val x = if (true) 1 else "s"
    val i: Int = <!TYPE_MISMATCH!>x<!>
}
