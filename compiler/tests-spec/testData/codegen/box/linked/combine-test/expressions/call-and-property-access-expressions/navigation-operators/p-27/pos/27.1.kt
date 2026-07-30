// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 27 -> sentence 27
 *                expressions, not-null-assertion-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: safe call mixed with non-null assertion returns value when chain is all non-null, throws NPE when intermediate nullable is null
 */

// TESTCASE NUMBER: 1
data class A(val b: B?)
data class B(val v: Int)

fun test(a: A?): Int = a?.b!!.v

fun box(): String {
    val a1 = A(B(42))
    if (test(a1) != 42) return "NOK: returns value when all non-null"
    val a2: A? = null
    try {
        test(a2)
        return "NOK: should throw NPE when a is null"
    } catch (e: NullPointerException) {
    }
    val a3 = A(null)
    try {
        test(a3)
        return "NOK: should throw NPE when b is null"
    } catch (e: NullPointerException) {
    }
    return "OK"
}
