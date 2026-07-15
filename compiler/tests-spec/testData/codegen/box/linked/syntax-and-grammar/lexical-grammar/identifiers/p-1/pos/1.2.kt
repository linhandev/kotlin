// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Letter Latin uppercase in identifier HelloWorld
 */
// TESTCASE NUMBER: 1
val HelloWorld = 2

fun box(): String = if (HelloWorld == 2) "OK" else "NOK"
