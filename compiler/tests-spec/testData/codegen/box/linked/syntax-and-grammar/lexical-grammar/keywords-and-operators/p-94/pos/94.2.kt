// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 94 -> sentence 94
 * NUMBER: 2
 * DESCRIPTION: BREAK token in for loop
 */
// TESTCASE NUMBER: 1
fun breakFor94(): String {
    for (i in 1..10) {
        if (i == 3) break
    }
    return "OK"
}

fun box(): String = breakFor94()
