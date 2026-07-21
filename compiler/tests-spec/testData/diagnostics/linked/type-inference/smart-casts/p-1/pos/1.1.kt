// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: flow-sensitive typing narrows compile-time type after is check
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any) {
    if (x is Int) {
        val y = <!DEBUG_INFO_SMARTCAST!>x<!> + 1
        println(y)
    }
}
