// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 83 -> sentence 83
 * NUMBER: 1
 * DESCRIPTION: ELSE token in if-else expression
 */
// TESTCASE NUMBER: 1

fun pickElse83(flag: Boolean): String = if (flag) "OK" else "NOK"

fun box(): String {
    return pickElse83(false).let { if (it == "NOK") "OK" else it }
}
