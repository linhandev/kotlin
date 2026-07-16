// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 93 -> sentence 93
 * NUMBER: 4
 * DESCRIPTION: CONTINUE token in do-while loop
 */
// TESTCASE NUMBER: 1
fun continueDoWhile93(): String {
    var steps = 0
    do {
        steps++
        if (steps == 1) continue
        return "OK"
    } while (steps < 3)
    return "NOK"
}

fun box(): String = continueDoWhile93()
