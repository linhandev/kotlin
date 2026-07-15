// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 97 -> sentence 97
 * NUMBER: 1
 * DESCRIPTION: IN token in range containment check
 */
// TESTCASE NUMBER: 1
fun containmentIn97(value: Int): String {
    return if (value in 1..100) "OK" else "NOK"
}

fun box(): String = containmentIn97(42)
