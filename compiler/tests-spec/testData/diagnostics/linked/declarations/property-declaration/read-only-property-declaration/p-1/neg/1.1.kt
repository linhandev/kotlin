// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, read-only-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: read-only val cannot be reassigned
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 1
    <!VAL_REASSIGNMENT!>x<!> = 2
}

class Holder {
    val y = "a"
    fun mutate() {
        <!VAL_REASSIGNMENT!>y<!> = "b"
    }
}
