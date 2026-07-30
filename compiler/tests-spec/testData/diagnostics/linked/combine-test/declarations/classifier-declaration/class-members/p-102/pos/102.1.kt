// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 102 -> sentence 102
 * NUMBER: 1
 * DESCRIPTION: A as Any == B infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class A
class B

fun case1() {
    checkSubtype<Boolean>((A() as Any) == B())
}
