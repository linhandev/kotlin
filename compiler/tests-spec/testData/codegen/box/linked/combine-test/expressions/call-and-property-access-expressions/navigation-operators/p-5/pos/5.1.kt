// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: chained safe call short-circuits at the first null in the chain, any null link makes the whole result null
 */

// TESTCASE NUMBER: 1
data class A(val b: B?)
data class B(val c: C?)
data class C(val v: Int)

fun test(a: A?): Int? = a?.b?.c?.v

fun box(): String {
    val fullChain = A(B(C(42)))
    if (test(fullChain) != 42) return "NOK: full chain"

    val bNull = A(null)
    if (test(bNull) != null) return "NOK: b is null"

    val cNull = A(B(null))
    if (test(cNull) != null) return "NOK: c is null"

    val aNull: A? = null
    if (test(aNull) != null) return "NOK: a is null"

    return "OK"
}
