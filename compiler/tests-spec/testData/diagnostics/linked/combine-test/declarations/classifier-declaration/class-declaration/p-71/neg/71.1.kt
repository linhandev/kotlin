// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 71 -> sentence 71
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 71 -> sentence 71
 * NUMBER: 1
 * DESCRIPTION: secondary constructor body cannot assign properties without primary delegation
 */

// TESTCASE NUMBER: 1
class User(val name: String) { var tag = ""; <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor(name: String, tag: String)<!> { this.tag = tag } }
