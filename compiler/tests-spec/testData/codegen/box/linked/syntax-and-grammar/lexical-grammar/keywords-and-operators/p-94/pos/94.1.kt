// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 94 -> sentence 94
 * NUMBER: 1
 * DESCRIPTION: BREAK token in while loop
 */
// TESTCASE NUMBER: 1
fun breakWhile94(): String {
    var count = 0
    while (true) {
        count++
        if (count == 2) break
    }
    return if (count == 2) "OK" else "NOK"
}

fun box(): String = breakWhile94()
