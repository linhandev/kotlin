// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, prefix-expressions, logical-not-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: !f invokes operator fun not on Flag
 */

// TESTCASE NUMBER: 1

class Flag(val v: Boolean) {
    var notCalled = false
    operator fun not(): Boolean {
        notCalled = true
        return !v
    }
}

fun box(): String {
    val f = Flag(false)
    if (!f != true) return "NOK"
    if (!f.notCalled) return "NOK"
    return "OK"
}
