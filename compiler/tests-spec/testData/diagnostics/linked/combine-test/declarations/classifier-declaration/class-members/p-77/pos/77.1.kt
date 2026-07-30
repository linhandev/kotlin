// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 77 -> sentence 77
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 77 -> sentence 77
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 77 -> sentence 77
 * NUMBER: 1
 * DESCRIPTION: overridden equals on data class drives ==
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int) {
    override fun equals(other: Any?): Boolean = false
}

fun case1() {
    checkSubtype<Boolean>(Data(42) == Data(42))
}
