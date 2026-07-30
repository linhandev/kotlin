// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 81 -> sentence 81
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 81 -> sentence 81
 *                expressions, equality-expressions, reference-equality-expressions -> paragraph 81 -> sentence 81
 * NUMBER: 1
 * DESCRIPTION: distinct equal-valued instances === infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box(val x: Int)

fun case1() {
    checkSubtype<Boolean>(Box(42) === Box(42))
}
