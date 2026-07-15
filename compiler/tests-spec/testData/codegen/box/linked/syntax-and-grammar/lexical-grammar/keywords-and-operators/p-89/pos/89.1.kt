// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 89 -> sentence 89
 * NUMBER: 1
 * DESCRIPTION: DO token in do-while loop
 */
// TESTCASE NUMBER: 1
fun doWhile89(): String {
    var count = 0
    do {
        count++
    } while (count < 1)
    return if (count == 1) "OK" else "NOK"
}

fun box(): String = doWhile89()
