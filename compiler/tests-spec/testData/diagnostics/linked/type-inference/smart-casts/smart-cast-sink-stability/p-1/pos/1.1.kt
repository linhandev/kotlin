// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-sink-stability -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: immutable local property is a stable smart cast sink
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: String? = "ok"
    if (x != null) {
        val len = <!DEBUG_INFO_SMARTCAST!>x<!>.length
        println(len)
    }
}
