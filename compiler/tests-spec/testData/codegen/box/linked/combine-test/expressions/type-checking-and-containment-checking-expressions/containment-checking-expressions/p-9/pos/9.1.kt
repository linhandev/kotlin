// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: in operator overload resolution selects more specific receiver type contains extension at runtime
 */

// TESTCASE NUMBER: 1
open class Box

class SpecificBox : Box()

operator fun Box.contains(x: Int): Boolean = false

operator fun SpecificBox.contains(x: Int): Boolean = true

fun testSpecific(x: Int): Boolean = x in SpecificBox()

fun testBox(x: Int): Boolean = x in Box()

fun box(): String {
    if (!testSpecific(1)) return "NOK"
    if (testBox(1)) return "NOK"
    return "OK"
}
