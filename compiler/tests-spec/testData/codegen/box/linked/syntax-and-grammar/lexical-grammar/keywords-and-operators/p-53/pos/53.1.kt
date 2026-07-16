// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 53 -> sentence 53
 * NUMBER: 1
 * DESCRIPTION: BREAK_AT token in break@loop from labeled for loop
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var steps = 0
    loop@ for (i in 1..3) {
        steps++
        if (i == 2) break@loop
    }
    return if (steps == 2) "OK" else "NOK"
}
