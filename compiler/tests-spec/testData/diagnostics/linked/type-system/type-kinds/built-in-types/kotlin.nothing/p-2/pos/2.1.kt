// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.nothing -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, subtyping, subtyping-rules -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Nothing is a subtype of Any, Int, String, Boolean, and Double
 * HELPERS: checkType
 */

fun nothingVal(): Nothing = TODO()

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Any>(nothingVal())
}

// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Int>(nothingVal())
}

// TESTCASE NUMBER: 3
fun case_3() {
    checkSubtype<String>(nothingVal())
}

// TESTCASE NUMBER: 4
fun case_4() {
    checkSubtype<Boolean>(nothingVal())
}

// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<Double>(nothingVal())
}
