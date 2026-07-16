// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 53 -> sentence 53
 * NUMBER: 3
 * DESCRIPTION: BREAK_AT token in break@scan exiting labeled for early
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val picked = mutableListOf<Int>()
    scan@ for (i in 1..10) {
        picked.add(i)
        if (i == 4) {
            break@scan
        }
    }
    return if (picked == listOf(1, 2, 3, 4)) "OK" else "NOK"
}
