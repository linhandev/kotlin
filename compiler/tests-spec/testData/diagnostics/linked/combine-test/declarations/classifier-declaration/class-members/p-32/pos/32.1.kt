// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 32 -> sentence 32
 *                expressions, indexing-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: single-index get infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Wrapper(val items: List<Int>) {
    operator fun get(index: Int) = items[index]
}

fun case1() {
    checkSubtype<Int>(Wrapper(listOf(1, 2, 3))[1])
}
