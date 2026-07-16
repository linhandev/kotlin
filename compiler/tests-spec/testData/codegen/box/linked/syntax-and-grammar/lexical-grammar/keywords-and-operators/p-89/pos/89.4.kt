// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 89 -> sentence 89
 * NUMBER: 4
 * DESCRIPTION: DO token in do-while with break
 */
// TESTCASE NUMBER: 1
fun doBreak89(): String {
    var steps = 0
    do {
        steps++
        if (steps == 1) break
    } while (true)
    return if (steps == 1) "OK" else "NOK"
}

fun box(): String = doBreak89()
