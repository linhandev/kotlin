// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: While-loop evaluates condition before every body evaluation including the first one
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val order = mutableListOf<String>()
    var count = 0
    fun cond(): Boolean {
        order += "cond"
        return count < 2
    }
    while (cond()) {
        order += "body"
        count++
    }
    return if (order == listOf("cond", "body", "cond", "body", "cond")) "OK" else "NOK: $order"
}
