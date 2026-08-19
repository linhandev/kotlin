// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 16 -> sentence 16
 *                type-inference, smart-casts -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: bare type is check with type-parameterized member access via smart cast
 */

// TESTCASE NUMBER: 1
interface Container<T>
class Box<T>(val items: List<T>) : Container<T>

fun test(c: Container<Int>): Int {
    if (c is Box) {
        return c.items.sum()
    }
    return -1
}

fun box(): String {
    val box: Container<Int> = Box(listOf(1, 2, 3))
    if (test(box) != 6) return "NOK"
    class OtherContainer : Container<Int>
    if (test(OtherContainer()) != -1) return "NOK"
    return "OK"
}
