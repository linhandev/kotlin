// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 87 -> sentence 87
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 87 -> sentence 87
 * NUMBER: 1
 * DESCRIPTION: Child == Parent with default equals infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Parent(val x: Int)
class Child(x: Int) : Parent(x)

fun case1() {
    checkSubtype<Boolean>(Child(42) == Parent(42))
}
