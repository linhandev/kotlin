// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, late-initialized-properties -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: lateinit on primitive type is forbidden
 */

// TESTCASE NUMBER: 1
class Counter {
    <!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> var count: Int
}
