// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Letter Latin lowercase in identifier hello
 */
// TESTCASE NUMBER: 1
val hello = 1

fun box(): String {
    val x = hello
    return if (x == 1) "OK" else "NOK"
}
