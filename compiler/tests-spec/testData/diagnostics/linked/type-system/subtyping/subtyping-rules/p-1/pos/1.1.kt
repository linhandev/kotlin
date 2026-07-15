// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-rules -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Nothing is a subtype of all well-formed Kotlin types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val n: Nothing = TODO()
    checkSubtype<Int>(n)
}

// TESTCASE NUMBER: 2
fun case_2() {
    val n: Nothing = TODO()
    checkSubtype<String>(n)
}

// TESTCASE NUMBER: 3
fun case_3() {
    val n: Nothing = TODO()
    checkSubtype<Any>(n)
}

// TESTCASE NUMBER: 4
fun case_4() {
    val n: Nothing = TODO()
    checkSubtype<Number>(n)
}

// TESTCASE NUMBER: 5
fun case_5() {
    val n: Nothing = TODO()
    checkSubtype<List<Int>>(n)
}
