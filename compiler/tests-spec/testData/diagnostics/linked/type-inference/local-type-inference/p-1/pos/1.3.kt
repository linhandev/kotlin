// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, local-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: smart cast effects are considered in local type inference
 * HELPERS: checkType
 */

fun <T> id142(value: T): T = value

// TESTCASE NUMBER: 1
fun case_1() {
    var a: Any? = null
    if (a == null) return
    val c = id142(<!DEBUG_INFO_SMARTCAST!>a<!>)
    checkSubtype<Any>(c)
}
