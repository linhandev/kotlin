// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: enum entries must have unique names
 */

// TESTCASE NUMBER: 1
enum class E {
    <!REDECLARATION!>name<!>,
    A
}
