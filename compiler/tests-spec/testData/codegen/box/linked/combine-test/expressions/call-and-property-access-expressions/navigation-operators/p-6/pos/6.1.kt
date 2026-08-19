// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: when only part of the chain is nullable, safe call is only needed on the nullable segment
 */

// TESTCASE NUMBER: 1
data class Outer(val inner: Inner?)
data class Inner(val x: Int)

fun test(o: Outer): Int? = o.inner?.x

fun box(): String {
    val withInner = Outer(Inner(10))
    if (test(withInner) != 10) return "NOK: inner exists"

    val withoutInner = Outer(null)
    if (test(withoutInner) != null) return "NOK: inner is null"

    return "OK"
}
