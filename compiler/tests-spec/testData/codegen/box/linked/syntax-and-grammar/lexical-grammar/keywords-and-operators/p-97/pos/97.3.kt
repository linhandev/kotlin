// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 97 -> sentence 97
 * NUMBER: 3
 * DESCRIPTION: IN token in collection containment check
 */
// TESTCASE NUMBER: 1
fun collectionIn97(value: String): String {
    return if (value in listOf("OK", "YES")) "OK" else "NOK"
}

fun box(): String = collectionIn97("OK")
