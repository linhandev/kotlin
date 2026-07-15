// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 115 -> sentence 115
 * NUMBER: 1
 * DESCRIPTION: EXTERNAL token in external top-level function declaration coexists with regular top-level function
 */
// TESTCASE NUMBER: 1
external fun nativeEcho115(input: String): String

fun regularEcho115(input: String): String = input

fun box(): String {
    val input = "external-115-1"
    return if (regularEcho115(input) == input) "OK" else "NOK"
}
