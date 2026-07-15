// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: COLONCOLON token used in property reference String::length
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val length = String::length
    return if (length("kotlin") == 6) "OK" else "NOK"
}
