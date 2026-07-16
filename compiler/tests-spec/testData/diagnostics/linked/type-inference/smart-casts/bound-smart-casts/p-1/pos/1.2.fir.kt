// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, bound-smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: alias set with three stable copies propagates smart cast to all members
 */

// TESTCASE NUMBER: 2
fun case_2() {
    var a: Any? = "ok"
    val b = a
    val c = a
    if (a is String) {
        val len1 = <!DEBUG_INFO_SMARTCAST!>b<!>.length
        val len2 = <!DEBUG_INFO_SMARTCAST!>c<!>.length
        println(len1 + len2)
    }
}
