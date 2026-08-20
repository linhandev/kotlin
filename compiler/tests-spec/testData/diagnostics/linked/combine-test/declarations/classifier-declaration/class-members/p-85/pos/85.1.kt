// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 85 -> sentence 85
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 85 -> sentence 85
 *                expressions, equality-expressions, reference-equality-expressions -> paragraph 85 -> sentence 85
 * NUMBER: 1
 * DESCRIPTION: class-typed nullable === infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box

fun ref(a: Box?, b: Box?): Boolean = a === b

fun case1() {
    checkSubtype<Boolean>(ref(null, null))
    checkSubtype<Boolean>(ref(null, Box()))
}
