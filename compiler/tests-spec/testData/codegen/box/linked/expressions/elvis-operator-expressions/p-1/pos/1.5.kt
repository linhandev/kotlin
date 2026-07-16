// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: x ?: sideEffect() skips sideEffect() when x is non-null "N"
 */

// TESTCASE NUMBER: 1

var called = false

fun sideEffect(): String {
    called = true
    return "OK"
}

fun box(): String {
    val x: String? = "N"
    called = false
    val r = x ?: sideEffect()
    if (called) return "NOK"
    if (r != "N") return "NOK"
    return "OK"
}
