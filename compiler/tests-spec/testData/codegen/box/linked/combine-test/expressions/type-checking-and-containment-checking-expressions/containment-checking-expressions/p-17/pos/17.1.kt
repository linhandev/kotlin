// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to generic extension contains with Number upper bound at runtime
 */

// TESTCASE NUMBER: 1
class Box<T : Number>(val threshold: T)

operator fun <T : Number> Box<T>.contains(x: T): Boolean = x.toDouble() >= threshold.toDouble()

fun test(x: Int): Boolean = x in Box(3)

fun box(): String {
    if (!test(5)) return "NOK"
    if (test(1)) return "NOK"
    if (!test(3)) return "NOK"
    if (test(2)) return "NOK"
    return "OK"
}
