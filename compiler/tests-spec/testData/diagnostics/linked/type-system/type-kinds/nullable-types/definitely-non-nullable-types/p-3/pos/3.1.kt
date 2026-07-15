// FIR_IDENTICAL
// LANGUAGE: +DefinitelyNonNullableTypes
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, definitely-non-nullable-types -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Definitely non-nullable types T & Any represent non-null values of type parameter T
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(t: T?, dnn: T & Any) {
    val a: T & Any = t!!
    checkSubtype<T & Any>(a)
}


// TESTCASE NUMBER: 2
fun <T> case_2(t: T?) {
    val b: T & Any = t!!
    checkSubtype<T & Any>(b)
}


// TESTCASE NUMBER: 3
typealias MyAny = kotlin.Any

fun <T> case_3(t: T?) {
    val c: T & MyAny = t!!
    checkSubtype<T & MyAny>(c)
}


// TESTCASE NUMBER: 4
fun <T> case_4(x: T & Any, y: T?) {
    val z: T & Any = y ?: x
    checkSubtype<T & Any>(z)
}


// TESTCASE NUMBER: 5
fun <T> case_5(t: T?, dnn: T & Any): T & Any {
    return dnn
}
