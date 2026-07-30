// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: extension operator get multi-index infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
operator fun List<Int>.get(i: Int, j: Int): Int = this[i] + this[j]

fun case1() {
    checkSubtype<Int>(listOf(1, 9)[0, 1])
}
