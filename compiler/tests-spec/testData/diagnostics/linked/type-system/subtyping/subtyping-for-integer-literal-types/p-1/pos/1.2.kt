// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Built-in integer types are supertypes of compatible integer literals
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Int = 0

// TESTCASE NUMBER: 2
fun case_2(): Byte = 1

// TESTCASE NUMBER: 3
fun case_3(): Short = 2

// TESTCASE NUMBER: 4
fun case_4(): Long = 3L

// TESTCASE NUMBER: 5
fun case_5() {
    val values: List<Int> = listOf(1, 2, 3)
    checkSubtype<List<Int>>(values)
}
