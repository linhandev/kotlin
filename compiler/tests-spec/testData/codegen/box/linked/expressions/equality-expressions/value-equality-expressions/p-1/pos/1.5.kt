// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, value-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: floating point NaN direct == differs from cast to Any? on JVM
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val nan = Double.NaN
    if (nan == nan) return "NOK"
    val a: Any? = nan
    val b: Any? = nan
    if (!(a == b)) return "NOK"
    return "OK"
}
