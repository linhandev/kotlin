// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: COMMA token used as separator in function parameters (a: Int, b: Int)
 */
// TESTCASE NUMBER: 1

fun add(a: Int, b: Int): Int = a + b

fun box(): String {
    return if (add(1, 2) == 3) "OK" else "NOK"
}
