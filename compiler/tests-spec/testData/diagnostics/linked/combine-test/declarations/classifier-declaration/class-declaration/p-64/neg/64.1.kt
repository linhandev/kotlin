// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 64 -> sentence 64
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 64 -> sentence 64
 * NUMBER: 1
 * DESCRIPTION: plain constructor parameter is not accessible as property
 */

// TESTCASE NUMBER: 1
class User(name: String)

fun test() = User("Ann").<!UNRESOLVED_REFERENCE!>name<!>
