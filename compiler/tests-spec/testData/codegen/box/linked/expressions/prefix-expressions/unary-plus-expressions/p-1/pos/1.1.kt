// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, prefix-expressions, unary-plus-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: +b invokes operator fun unaryPlus on Wrapper
 */

// TESTCASE NUMBER: 1

class Wrapper(val v: Int) {
    var plusCalled = false
    operator fun unaryPlus(): Int {
        plusCalled = true
        return v
    }
}

fun box(): String {
    val b = Wrapper(3)
    if (+b != 3 || !b.plusCalled) return "NOK"
    return "OK"
}
