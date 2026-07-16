// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, multiplicative-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Wrapper % 3 invokes operator fun rem with operand 3
 */

// TESTCASE NUMBER: 1

class Wrapper(val value: Int) {
    var lastArg: Int? = null
    operator fun rem(other: Int): Int {
        lastArg = other
        return value % other
    }
}

fun box(): String {
    val w = Wrapper(7)
    if (w % 3 != 1) return "NOK"
    if (w.lastArg != 3) return "NOK"
    return "OK"
}
