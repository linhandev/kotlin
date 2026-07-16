// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 90 -> sentence 90
 * NUMBER: 2
 * DESCRIPTION: WHILE token in while loop with break
 */
// TESTCASE NUMBER: 1
fun whileBreak90(): String {
    var steps = 0
    while (true) {
        steps++
        if (steps == 1) break
    }
    return if (steps == 1) "OK" else "NOK"
}

fun box(): String = whileBreak90()
