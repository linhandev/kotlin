// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.enum -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: enum compareTo and enumValueOf reject non-matching operands
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
enum class Planet { MERCURY, VENUS, EARTH }
fun case_1() {
    Planet.MERCURY.compareTo(<!TYPE_MISMATCH!>Any()<!>)
}


// TESTCASE NUMBER: 2
fun case_2() {
    Planet.MERCURY.compareTo(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>true<!>)
}


// TESTCASE NUMBER: 3
fun case_3() {
    enumValueOf<Planet>(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
}
