// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 52 -> sentence 52
 * NUMBER: 1
 * DESCRIPTION: CONTINUE_AT token in continue@loop from labeled while with inner for
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var visits = 0
    loop@ while (visits < 5) {
        visits++
        for (i in 1..3) {
            if (i == 2) {
                continue@loop
            }
        }
        return "NOK"
    }
    return if (visits == 5) "OK" else "NOK"
}
