// FIR_IDENTICAL
// DIAGNOSTICS: -UNSUPPORTED -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, dynamic-type -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: dynamic type represents flexible type kotlin.Nothing..kotlin.Any? and accepts any value
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: dynamic = 1
    val y: Any? = x
    checkSubtype<Any?>(x)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x: dynamic = "test"
    checkSubtype<Any?>(x)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: dynamic = null
    val y: Any? = x
    checkSubtype<Any?>(x)
}


// TESTCASE NUMBER: 4
fun acceptDynamic4(d: dynamic): Any? = d

fun case_4() {
    acceptDynamic4(1)
    acceptDynamic4("ok")
}


// TESTCASE NUMBER: 5
fun case_5(x: dynamic) {
    checkSubtype<Any?>(x)
    val z: Any? = x
}
