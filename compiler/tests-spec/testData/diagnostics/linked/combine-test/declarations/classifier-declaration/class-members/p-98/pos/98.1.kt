// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 98 -> sentence 98
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 98 -> sentence 98
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 98 -> sentence 98
 * NUMBER: 1
 * DESCRIPTION: multi-property data class == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int, val y: String)

fun case1() {
    checkSubtype<Boolean>(Data(42, "a") == Data(42, "a"))
}
