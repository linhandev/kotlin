// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-sink-stability -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: directSinkOk allows smart cast at direct sink without nested redefinition on path
 */

// TESTCASE NUMBER: 1
fun case_1() {
    var x: Int? = 42
    if (x != null) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
    }
    run {
        x = null
    }
}
