// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 52 -> sentence 52
 * NUMBER: 2
 * DESCRIPTION: CONTINUE_AT token in continue@scan skipping even values in labeled for
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val picked = mutableListOf<Int>()
    scan@ for (i in 1..6) {
        if (i % 2 == 0) {
            continue@scan
        }
        picked.add(i)
    }
    return if (picked == listOf(1, 3, 5)) "OK" else "NOK"
}
