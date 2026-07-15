// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 45 -> sentence 45
 * NUMBER: 2
 * DESCRIPTION: EXCL_EQ token in when branch when (x != 10)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 5
    when {
        x != 10 -> return "OK"
        else -> return "NOK"
    }
}
