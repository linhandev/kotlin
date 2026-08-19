// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to generic extension contains with type argument inferred from Box constructor and element type at runtime
 */

// TESTCASE NUMBER: 1
class Box<T>(val list: List<T>)

operator fun <T> Box<T>.contains(x: T): Boolean = x in list

fun test(x: Int): Boolean = x in Box(listOf(1, 2, 3))

fun box(): String {
    if (!test(2)) return "NOK"
    if (test(4)) return "NOK"
    if (!test(1)) return "NOK"
    if (test(6)) return "NOK"
    return "OK"
}
