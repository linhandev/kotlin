// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 94 -> sentence 94
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 94 -> sentence 94
 * NUMBER: 1
 * DESCRIPTION: nullable param == null infers Boolean on both paths
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box

fun test(b: Box?): Boolean = b == null

fun case1() {
    checkSubtype<Boolean>(test(null))
    checkSubtype<Boolean>(test(Box()))
}
