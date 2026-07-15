// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 97 -> sentence 97
 * NUMBER: 2
 * DESCRIPTION: IN token in for-in loop header
 */
// TESTCASE NUMBER: 1
fun forIn97(): String {
    val expected = "in-97-2"
    for (item in listOf("NOK", expected)) {
        if (item == expected) return item
    }
    return "NOK"
}

fun box(): String {
    val expected = "in-97-2"
    if (forIn97() != expected) return "NOK"
    return "OK"
}
