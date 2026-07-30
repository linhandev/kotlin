// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: NaN IEEE == false while equals true
 */

// TESTCASE NUMBER: 1
fun testEq(): Boolean = Double.NaN == Double.NaN
fun testEquals(): Boolean = Double.NaN.equals(Double.NaN)

fun box(): String {
    if (testEq()) return "NOK"
    if (!testEquals()) return "NOK"
    return "OK"
}
