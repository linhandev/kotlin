// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 31 -> sentence 31
 * NUMBER: 2
 * DESCRIPTION: RANGE token used in character range 'a'..'c'
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val chars = ('a'..'c').toList()
    return if (chars == listOf('a', 'b', 'c')) "OK" else "NOK"
}
