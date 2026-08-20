// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: recursive Comparable bound with user-defined Comparable
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Rank(val n: Int) : Comparable<Rank> { override fun compareTo(<!PARAMETER_NAME_CHANGED_ON_OVERRIDE!>o<!>: Rank) = n.compareTo(o.n) }

class Ordered<T : Comparable<T>>(val a: T, val b: T) { fun max() = if (a >= b) a else b }

fun case1() {
    checkSubtype<Int>(Ordered(Rank(1), Rank(3)).max().n)
}
