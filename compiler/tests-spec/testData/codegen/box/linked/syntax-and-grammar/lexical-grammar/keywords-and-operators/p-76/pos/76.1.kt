// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 76 -> sentence 76
 * NUMBER: 1
 * DESCRIPTION: COMPANION token in companion object declaration
 */
// TESTCASE NUMBER: 1

class Host76 {
    companion object {
        fun value(): String = "kw-76-76-1"
    }
}

fun box(): String {
    val expected = "kw-76-76-1"
    val result = Host76.value()
    if (result != expected) return "NOK"
    return "OK"
}
