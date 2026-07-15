// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 48 -> sentence 48
 * NUMBER: 2
 * DESCRIPTION: EQEQ token in when branch when (x == 5)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 5
    when {
        x == 5 -> return "OK"
        else -> return "NOK"
    }
}
