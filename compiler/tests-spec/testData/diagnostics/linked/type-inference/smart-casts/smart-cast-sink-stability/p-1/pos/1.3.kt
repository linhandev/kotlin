// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-sink-stability -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: nestedSinkOk allows smart cast at nested sink when direct redefinitions precede sink
 */

// TESTCASE NUMBER: 1
fun getNullableInt1413(): Int? = 1

fun case_1() {
    var x: Int?
    x = 42
    x = getNullableInt1413()
    run {
        if (x != null) {
            <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
        }
    }
}
