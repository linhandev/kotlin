// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 18 -> sentence 18
 *                type-inference, smart-casts -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: data class sealed subclass properties are accessible after is smart cast
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class PairVal(val a: Int, val b: Int) : Expr()

fun test(e: Expr): Int = when (e) {
    is PairVal -> e.a + e.b
}

fun box(): String {
    if (test(PairVal(2, 3)) != 5) return "NOK"
    return "OK"
}
