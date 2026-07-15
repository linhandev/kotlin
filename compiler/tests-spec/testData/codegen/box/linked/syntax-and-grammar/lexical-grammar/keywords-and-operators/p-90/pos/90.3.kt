// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 90 -> sentence 90
 * NUMBER: 3
 * DESCRIPTION: WHILE token in labeled while loop
 */
// TESTCASE NUMBER: 1
fun labeledWhile90(): String {
    var count = 0
    loop@ while (count < 3) {
        count++
        if (count == 2) break@loop
    }
    return if (count == 2) "OK" else "NOK"
}

fun box(): String = labeledWhile90()
