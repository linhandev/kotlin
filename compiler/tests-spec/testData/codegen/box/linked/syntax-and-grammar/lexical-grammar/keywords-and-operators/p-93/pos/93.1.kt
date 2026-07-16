// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 93 -> sentence 93
 * NUMBER: 1
 * DESCRIPTION: CONTINUE token in while loop
 */
// TESTCASE NUMBER: 1
fun continueWhile93(): String {
    var count = 0
    while (count < 5) {
        count++
        if (count < 3) continue
        return "OK"
    }
    return "NOK"
}

fun box(): String = continueWhile93()
