// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 46 -> sentence 46
 *                type-inference, introduction-1 -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: member function on instantiated generic class inherits type argument
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T) {
    fun get(): T = v
}

fun box(): String {
    val b1: Box<String> = Box("hello")
    if (b1.get() != "hello") return "NOK"
    val b2: Box<Int> = Box(42)
    if (b2.get() != 42) return "NOK"
    return "OK"
}
