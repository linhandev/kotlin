// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 93 -> sentence 93
 * NUMBER: 2
 * DESCRIPTION: CONTINUE token in for loop
 */
// TESTCASE NUMBER: 1
fun continueFor93(): String {
    for (i in 1..5) {
        if (i < 4) continue
        return "OK"
    }
    return "NOK"
}

fun box(): String = continueFor93()
