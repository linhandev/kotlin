// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 72 -> sentence 72
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 72 -> sentence 72
 * NUMBER: 1
 * DESCRIPTION: default == on distinct class instances infers Boolean false path
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box

fun case1() {
    checkSubtype<Boolean>(Box() == Box())
}
