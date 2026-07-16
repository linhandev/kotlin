// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 92 -> sentence 92
 * NUMBER: 2
 * DESCRIPTION: RETURN token in early return from conditional
 */
// TESTCASE NUMBER: 1
fun returnEarly92(flag: Boolean): String {
    return if (flag) "OK" else "NOK"
}

fun box(): String = returnEarly92(true)
