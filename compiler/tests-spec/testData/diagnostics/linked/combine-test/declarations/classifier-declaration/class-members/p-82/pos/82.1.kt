// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 82 -> sentence 82
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 82 -> sentence 82
 *                expressions, equality-expressions, reference-equality-expressions -> paragraph 82 -> sentence 82
 * NUMBER: 1
 * DESCRIPTION: identical reference === infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box

fun case1() {
    checkSubtype<Boolean>(Box().let { it === it })
}
