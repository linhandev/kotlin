// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNREACHABLE_CODE -REDUNDANT_PROJECTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.nothing -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, subtyping, subtyping-rules -> paragraph 2 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Nothing is a subtype of Byte, Short, Long, and custom classifier types
 * HELPERS: checkType
 */

fun nothingVal(): Nothing = TODO()

class CustomClass
interface CustomInterface

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Byte>(nothingVal())
}

// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Short>(nothingVal())
}

// TESTCASE NUMBER: 3
fun case_3() {
    checkSubtype<Long>(nothingVal())
}

// TESTCASE NUMBER: 4
fun case_4() {
    checkSubtype<CustomClass>(nothingVal())
}

// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<CustomInterface>(nothingVal())
}
