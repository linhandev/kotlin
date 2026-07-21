// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: cannot override final Enum compareTo equals hashCode
 */

// TESTCASE NUMBER: 1
enum class E {
    ENTRY;

    <!OVERRIDING_FINAL_MEMBER!>override<!> fun compareTo(other: E) = -1
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun equals(other: Any?) = true
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun hashCode() = -1
}
