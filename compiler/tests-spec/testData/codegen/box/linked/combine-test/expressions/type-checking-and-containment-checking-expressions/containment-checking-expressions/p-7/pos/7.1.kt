// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: in operator overload resolution selects most specific contains overload at runtime
 */

// TESTCASE NUMBER: 1
class Box

operator fun Box.contains(x: Int): Boolean = true

operator fun Box.contains(x: Number): Boolean = false

fun testInt(x: Int): Boolean = x in Box()

fun testNumber(x: Number): Boolean = x in Box()

fun box(): String {
    if (!testInt(1)) return "NOK"
    if (testNumber(1.0)) return "NOK"
    return "OK"
}
