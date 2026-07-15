// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 21 -> sentence 21
 * NUMBER: 2
 * DESCRIPTION: COLON token used in function parameter type annotation fun f(x: Int)
 */
// TESTCASE NUMBER: 1

fun double(x: Int): Int = x * 2

fun box(): String {
    return if (double(21) == 42) "OK" else "NOK"
}
