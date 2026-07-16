// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 94 -> sentence 94
 * NUMBER: 3
 * DESCRIPTION: BREAK token in labeled break@loop
 */
// TESTCASE NUMBER: 1
fun labeledBreak94(): String {
    var result = "NOK"
    loop@ for (i in 1..5) {
        if (i == 2) {
            result = "OK"
            break@loop
        }
    }
    return result
}

fun box(): String = labeledBreak94()
