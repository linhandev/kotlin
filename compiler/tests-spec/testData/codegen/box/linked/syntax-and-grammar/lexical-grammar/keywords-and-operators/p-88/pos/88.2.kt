// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 88 -> sentence 88
 * NUMBER: 2
 * DESCRIPTION: FOR token in for-in loop over collection
 */
// TESTCASE NUMBER: 1
fun listFor88(): String {
    val expected = "for-in-88-2"
    val items = listOf("NOK", expected)
    for (item in items) {
        if (item == expected) return item
    }
    return "NOK"
}

fun box(): String {
    val expected = "for-in-88-2"
    if (listFor88() != expected) return "NOK"
    return "OK"
}
