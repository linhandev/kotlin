// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: MOD token used in modulo operation a % b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 10 % 3
    val b = 20 % 7
    return if (a == 1 && b == 6) "OK" else "NOK"
}
