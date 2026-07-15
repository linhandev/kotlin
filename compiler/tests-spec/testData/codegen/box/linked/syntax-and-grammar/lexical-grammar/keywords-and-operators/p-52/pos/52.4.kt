// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 52 -> sentence 52
 * NUMBER: 4
 * DESCRIPTION: CONTINUE_AT token in continue@body from labeled do-while loop
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    var sum = 0
    body@ do {
        i++
        if (i == 3) {
            continue@body
        }
        sum += i
    } while (i < 5)
    return if (sum == 1 + 2 + 4 + 5) "OK" else "NOK"
}
