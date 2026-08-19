// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, function-declaration, extension-function-declaration -> paragraph 10 -> sentence 10
 *                type-system, type-kinds, type-parameters -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to generic extension contains with type argument inference at runtime
 */

// TESTCASE NUMBER: 1
class Box<T>(val values: Set<T>)

operator fun <T> Box<T>.contains(x: T): Boolean = x in values

fun test(x: Int): Boolean = x in Box(setOf(1, 2, 3))

fun box(): String {
    if (!test(2)) return "NOK"
    if (test(4)) return "NOK"
    if (!test(1)) return "NOK"
    if (test(5)) return "NOK"
    return "OK"
}
