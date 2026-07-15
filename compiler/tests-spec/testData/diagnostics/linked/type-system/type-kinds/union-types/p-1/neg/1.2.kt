// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, union-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Union types are not denotable and cannot be used as explicit type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x = if (true) 1 else "a"
    checkSubtype<Int>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x = if (true) true else 1
    checkSubtype<Boolean>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x = if (true) 1.0 else 2L
    checkSubtype<Int>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val x = if (true) 'a' else "b"
    checkSubtype<Char>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val x = if (true) Float.NaN else Double.NaN
    checkSubtype<Float>(<!TYPE_MISMATCH!>x<!>)
}
