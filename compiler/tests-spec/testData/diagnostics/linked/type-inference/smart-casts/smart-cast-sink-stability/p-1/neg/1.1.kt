// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -VARIABLE_WITH_REDUNDANT_INITIALIZER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-sink-stability -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: directSinkBad blocks smart cast after nested redefinition on path to direct sink
 */

// TESTCASE NUMBER: 1
fun case_1() {
    var x: Int? = 42
    run {
        x = null
    }
    if (x != null) {
        <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
    }
}
