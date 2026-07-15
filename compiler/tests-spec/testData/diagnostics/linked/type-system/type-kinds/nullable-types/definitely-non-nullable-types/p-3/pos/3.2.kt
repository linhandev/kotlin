// FIR_IDENTICAL
// LANGUAGE: +DefinitelyNonNullableTypes
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, definitely-non-nullable-types -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: DNN types are subtypes of their nullable counterparts and usable as non-null T
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(x: T & Any): T {
    return x
}


// TESTCASE NUMBER: 2
fun <T> case_2(x: T & Any) {
    checkSubtype<T>(x)
}


// TESTCASE NUMBER: 3
fun <T> case_3(x: T & Any) {
    checkSubtype<Any>(x)
}


// TESTCASE NUMBER: 4
fun <T> case_4(x: T & Any) {
    checkSubtype<T>(x)
}


// TESTCASE NUMBER: 5
fun <T> case_5(x: T & Any, y: T?) {
    val z: T? = x
    checkSubtype<T?>(z)
}
