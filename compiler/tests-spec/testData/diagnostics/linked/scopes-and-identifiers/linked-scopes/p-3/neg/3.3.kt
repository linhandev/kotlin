// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: Outer636.Nested.read() reference to outer value reports UNRESOLVED_REFERENCE
 */

// TESTCASE NUMBER: 1
class Outer636 {
    val value = 1

    class Nested {
        fun read(): Int = <!UNRESOLVED_REFERENCE!>value<!>
    }
}
