// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: enum entry initialization is required when enum class has constructor parameters
 */

// TESTCASE NUMBER: 1
enum class E(val x: Int) {
    <!ENUM_ENTRY_SHOULD_BE_INITIALIZED!>A,<!>
    B(1)
}
