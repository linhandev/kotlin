// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 21 -> sentence 21
 * NUMBER: 3
 * DESCRIPTION: COLON token used in function return type annotation fun f(): String
 */
// TESTCASE NUMBER: 1

fun greet(): String = "hello"

fun box(): String {
    return if (greet() == "hello") "OK" else "NOK"
}
