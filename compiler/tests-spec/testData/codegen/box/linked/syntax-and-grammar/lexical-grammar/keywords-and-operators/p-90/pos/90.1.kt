// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 90 -> sentence 90
 * NUMBER: 1
 * DESCRIPTION: WHILE token in while loop
 */
// TESTCASE NUMBER: 1
fun while90(): String {
    var count = 0
    while (count < 1) {
        count++
    }
    return if (count == 1) "OK" else "NOK"
}

fun box(): String = while90()
