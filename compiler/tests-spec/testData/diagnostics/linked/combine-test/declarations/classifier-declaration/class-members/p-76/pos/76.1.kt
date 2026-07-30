// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 76 -> sentence 76
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 76 -> sentence 76
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 76 -> sentence 76
 * NUMBER: 1
 * DESCRIPTION: data class == ignores body var property differences
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int) {
    var y: Int = 0
}

fun case1() {
    checkSubtype<Boolean>(Data(42).also { it.y = 1 } == Data(42))
}
