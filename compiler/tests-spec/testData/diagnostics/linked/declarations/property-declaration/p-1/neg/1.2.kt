// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: abstract property in non-abstract class
 */

// TESTCASE NUMBER: 1
class Concrete {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val missing: Int<!>
}
