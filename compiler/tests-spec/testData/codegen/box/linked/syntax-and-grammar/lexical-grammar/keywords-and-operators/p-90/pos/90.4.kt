// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 90 -> sentence 90
 * NUMBER: 4
 * DESCRIPTION: WHILE token in while loop with runtime condition
 */
// TESTCASE NUMBER: 1
fun whileCondition90(limit: Int): String {
    var value = 0
    while (value < limit) {
        value++
    }
    return if (value == limit) "OK" else "NOK"
}

fun box(): String = whileCondition90(42)
