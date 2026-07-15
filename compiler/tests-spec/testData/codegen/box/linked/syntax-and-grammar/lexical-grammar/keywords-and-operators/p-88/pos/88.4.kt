// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 88 -> sentence 88
 * NUMBER: 4
 * DESCRIPTION: FOR token in for loop with indices
 */
// TESTCASE NUMBER: 1
fun indexFor88(): String {
    val expected = "for-index-88-4"
    val data = arrayOf("A", expected)
    for (index in data.indices) {
        if (data[index] == expected) return data[index]
    }
    return "NOK"
}

fun box(): String {
    if (indexFor88() != "for-index-88-4") return "NOK"
    return "OK"
}
