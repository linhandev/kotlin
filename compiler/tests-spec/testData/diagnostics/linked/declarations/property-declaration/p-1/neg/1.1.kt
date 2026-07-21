// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: top-level and member val without initializer in non-abstract context must be initialized
 */

// TESTCASE NUMBER: 1
<!MUST_BE_INITIALIZED!>val noInit: Int<!>

// TESTCASE NUMBER: 2
class C {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val memberNoInit: Int<!>
}
