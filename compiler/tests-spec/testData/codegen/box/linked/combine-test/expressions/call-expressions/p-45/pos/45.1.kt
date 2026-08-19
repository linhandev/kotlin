// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 45 -> sentence 45
 *                type-inference, introduction-1 -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: class type parameter inferred from constructor arguments
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T)

fun box(): String {
    val b1 = Box(1)
    if (b1.v != 1) return "NOK"
    val b2 = Box("hello")
    if (b2.v != "hello") return "NOK"
    val b3 = Box(true)
    if (b3.v != true) return "NOK"
    return "OK"
}
