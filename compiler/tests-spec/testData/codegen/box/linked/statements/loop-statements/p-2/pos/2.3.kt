// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: Do-while-loop evaluates body before condition on every iteration
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val order = mutableListOf<String>()
    var count = 0
    fun cond(): Boolean {
        order += "cond"
        return count < 3
    }
    do {
        order += "body"
        count++
    } while (cond())
    return if (order == listOf("body", "cond", "body", "cond", "body", "cond")) "OK" else "NOK: $order"
}
