// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, union-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Union type LUB cannot be narrowed to incompatible specific types
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x = if (true) true else "x"
    val y: Boolean = <!TYPE_MISMATCH!>x<!>
}
