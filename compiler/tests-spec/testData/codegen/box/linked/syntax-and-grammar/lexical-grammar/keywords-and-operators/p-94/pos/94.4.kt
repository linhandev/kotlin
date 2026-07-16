// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 94 -> sentence 94
 * NUMBER: 4
 * DESCRIPTION: BREAK token in do-while loop
 */
// TESTCASE NUMBER: 1
fun breakDoWhile94(): String {
    var count = 0
    do {
        count++
        break
    } while (false)
    return if (count == 1) "OK" else "NOK"
}

fun box(): String = breakDoWhile94()
