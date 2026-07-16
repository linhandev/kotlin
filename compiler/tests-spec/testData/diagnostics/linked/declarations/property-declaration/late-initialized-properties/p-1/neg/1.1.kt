// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, late-initialized-properties -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lateinit is only applicable to mutable non-primitive member or top-level properties
 */

// TESTCASE NUMBER: 1
class Service {
    <!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> val resource: String
}

// TESTCASE NUMBER: 2
class Counter {
    <!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> var count: Int
}

// TESTCASE NUMBER: 3
class NullableHolder {
    <!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> var label: String?
}
