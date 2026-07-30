// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 89 -> sentence 89
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 89 -> sentence 89
 * NUMBER: 1
 * DESCRIPTION: == and equals agree and infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val x: Int)

fun case1(a: Box, b: Box) {
    checkSubtype<Boolean>((a == b) == a.equals(b))
}
