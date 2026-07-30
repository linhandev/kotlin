// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 73 -> sentence 73
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 73 -> sentence 73
 * NUMBER: 1
 * DESCRIPTION: default == on same reference infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box

fun case1() {
    checkSubtype<Boolean>(Box().let { it == it })
}
