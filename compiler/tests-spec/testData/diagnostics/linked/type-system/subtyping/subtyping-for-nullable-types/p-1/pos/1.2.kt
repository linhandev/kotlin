// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Subtyping by nullability holds for compatible nullable types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: String? = "x"
    val b: Any? = a
    checkSubtype<Any?>(b)
}

// TESTCASE NUMBER: 2
fun case_2() {
    val a: Int? = null
    val b: Number? = a
    checkSubtype<Number?>(b)
}

// TESTCASE NUMBER: 3
fun case_3(x: String?) {
    val y: Any? = x
    checkSubtype<Any?>(y)
}

// TESTCASE NUMBER: 4
fun case_4() {
    val list: List<String>? = null
    checkSubtype<List<String>?>(list)
}

// TESTCASE NUMBER: 5
fun case_5() {
    val x: Any? = 42
    checkSubtype<Any?>(x)
}
