// FIR_IDENTICAL
// DIAGNOSTICS: -UNSUPPORTED -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -REDUNDANT_NULLABLE -REIFIED_TYPE_FORBIDDEN_SUBSTITUTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, dynamic-type -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: dynamic type can be used in parameters, returns, and nullable Any? contexts
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun returnDynamic6(): dynamic = 1

fun case_1() {
    checkSubtype<Any?>(returnDynamic6())
}


// TESTCASE NUMBER: 2
fun case_2(x: dynamic, y: dynamic?) {
    checkSubtype<Any?>(x)
    checkSubtype<Any?>(y)
}


// TESTCASE NUMBER: 3
class Holder8 {
    var value: dynamic = 0
}

fun case_3(h: Holder8) {
    h.value = "changed"
    checkSubtype<Any?>(h.value)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val values: Array<dynamic> = arrayOf(1, "two")
    checkSubtype<Any?>(values[0])
}


// TESTCASE NUMBER: 5
fun case_5(d: dynamic) {
    val copy: dynamic = d
    checkSubtype<Any?>(copy)
}
