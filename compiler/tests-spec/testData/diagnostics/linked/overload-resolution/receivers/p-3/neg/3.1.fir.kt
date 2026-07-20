// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: labeled this-expression cannot refer to unavailable receiver label
 */

// TESTCASE NUMBER: 1
class Outer1103

fun case_1() {
    with(Outer1103()) label@{
        val x = this<!UNRESOLVED_LABEL!>@Missing1103<!>
    }
}
