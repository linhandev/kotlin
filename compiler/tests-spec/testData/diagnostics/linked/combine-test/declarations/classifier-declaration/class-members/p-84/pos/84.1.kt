// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 84 -> sentence 84
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 84 -> sentence 84
 * NUMBER: 1
 * DESCRIPTION: class-typed nullable == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box

fun eq(a: Box?, b: Box?): Boolean = a == b

fun case1() {
    checkSubtype<Boolean>(eq(null, null))
    checkSubtype<Boolean>(eq(null, Box()))
}
