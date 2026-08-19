/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: compound assignment plusAssign is preferred over plus desugaring
 */

// TESTCASE NUMBER: 1
class Box(var v: Int) {
    operator fun plusAssign(i: Int) {
        v += i
    }
}

fun test(): Int {
    val b = Box(1)
    b += 2
    return b.v
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
