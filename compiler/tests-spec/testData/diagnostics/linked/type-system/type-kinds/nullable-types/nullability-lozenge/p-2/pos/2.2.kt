// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, nullability-lozenge -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Nullable subtyping chains and generic nullability lozenge relations
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: Int?) {
    val y: Int? = x
    checkSubtype<Int?>(y)
}


// TESTCASE NUMBER: 2
fun <T> case_2(x: T?) {
    val copy: T? = x
    checkSubtype<T?>(copy)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Double = 1.0
    val y: Any? = x
    checkSubtype<Any?>(y)
}


// TESTCASE NUMBER: 4
fun <T : Any> case_4(x: T?) {
    val y: Any? = x
    checkSubtype<Any?>(y)
}


// TESTCASE NUMBER: 5
fun case_5(x: Char) {
    val y: Char? = x
    checkSubtype<Char?>(y)
}
