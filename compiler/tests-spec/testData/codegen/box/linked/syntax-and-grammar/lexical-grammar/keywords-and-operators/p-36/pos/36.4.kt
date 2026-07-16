// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 36 -> sentence 36
 * NUMBER: 4
 * DESCRIPTION: AT_POST_WS token in labeled do-while loop body@ and break@body
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    body@ do {
        count++
        if (count == 2) {
            break@body
        }
    } while (true)
    return if (count == 2) "OK" else "NOK"
}
