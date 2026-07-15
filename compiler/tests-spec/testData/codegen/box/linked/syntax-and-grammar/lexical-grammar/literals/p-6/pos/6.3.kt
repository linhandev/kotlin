// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 6 -> sentence 6
 * NUMBER: 3
 * DESCRIPTION: RealLiteral as DoubleLiteral with omitted whole part .5
 */
// TESTCASE NUMBER: 1
fun box(): String = if (.5 == 0.5) "OK" else "NOK"
