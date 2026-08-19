// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, function-declaration, extension-function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: in operator prefers class member contains over extension contains at runtime
 */

// TESTCASE NUMBER: 1
class Box {
    val list = listOf(1, 2, 3)
    operator fun contains(x: Int): Boolean = x in list
}

operator fun Box.contains(x: Int): Boolean = false

fun test(x: Int): Boolean = x in Box()

fun box(): String {
    if (!test(2)) return "NOK"
    if (test(4)) return "NOK"
    if (!test(1)) return "NOK"
    if (test(5)) return "NOK"
    return "OK"
}
