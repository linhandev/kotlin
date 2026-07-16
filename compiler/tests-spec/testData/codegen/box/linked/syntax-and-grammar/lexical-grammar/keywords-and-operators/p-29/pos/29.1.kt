// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: ARROW token used in lambda expression { x -> x * 2 }
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val double = { x: Int -> x * 2 }
    return if (double(21) == 42) "OK" else "NOK"
}
