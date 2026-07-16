// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: enum entry override with mismatched signature
 */

// TESTCASE NUMBER: 1
enum class E {
    A {
        <!NOTHING_TO_OVERRIDE!>override<!> fun bar() {}
    };

    fun baz() {}
}
