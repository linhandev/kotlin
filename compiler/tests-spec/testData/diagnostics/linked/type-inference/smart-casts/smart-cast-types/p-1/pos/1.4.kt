// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-types -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: smart cast participates in type inference when rhs is not direct property initializer
 * HELPERS: checkType
 */

fun <T> id1412(value: T): T = value

// TESTCASE NUMBER: 1
fun case_1() {
    var a: Any? = null
    if (a == null) return
    var c = id1412(<!DEBUG_INFO_SMARTCAST!>a<!>)
    checkSubtype<Any>(c)
}
