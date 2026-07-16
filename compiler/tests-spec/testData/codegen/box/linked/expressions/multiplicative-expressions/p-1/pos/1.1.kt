// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, multiplicative-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Wrapper / 2 invokes operator fun div with operand 2
 */

// TESTCASE NUMBER: 1

class Wrapper(val value: Int) {
    var lastArg: Int? = null
    operator fun div(other: Int): Int {
        lastArg = other
        return value / other
    }
}

fun box(): String {
    val w = Wrapper(6)
    if (w / 2 != 3) return "NOK"
    if (w.lastArg != 2) return "NOK"
    return "OK"
}
