// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: recursive Comparable bound with user-defined Comparable
 */

// TESTCASE NUMBER: 1
class Rank(val n: Int) : Comparable<Rank> { override fun compareTo(o: Rank) = n.compareTo(o.n) }

class Ordered<T : Comparable<T>>(val a: T, val b: T) { fun max() = if (a >= b) a else b }

fun test() = Ordered(Rank(1), Rank(3)).max().n

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
