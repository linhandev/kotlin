// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: greater-or-equal and less-or-equal use compareTo
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Ver(val n: Int) : Comparable<Ver> {
    override fun compareTo(other: Ver): Int = n.compareTo(other.n)
}

fun case_1(): Boolean = Ver(2) >= Ver(1) && Ver(1) <= Ver(2)

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
}
