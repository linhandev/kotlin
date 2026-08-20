// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 105 -> sentence 105
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 105 -> sentence 105
 *                expressions, indexing-expressions -> paragraph 105 -> sentence 105
 * NUMBER: 1
 * DESCRIPTION: class member get without operator does not participate in indexing
 */

// TESTCASE NUMBER: 1
class Wrapper(val items: List<Int>) {
    fun get(index: Int) = items[index]
}

fun test() = <!OPERATOR_MODIFIER_REQUIRED!>Wrapper(listOf(1, 2))[0]<!>
