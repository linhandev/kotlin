// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: smart cast after null check avoids explicit cast on nullable receiver
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any?) {
    if (x != null) {
        val y = <!DEBUG_INFO_SMARTCAST!>x<!>.hashCode()
        println(y)
    }
}
