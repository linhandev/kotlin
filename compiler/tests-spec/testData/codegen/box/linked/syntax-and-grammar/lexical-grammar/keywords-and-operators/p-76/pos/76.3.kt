// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 76 -> sentence 76
 * NUMBER: 3
 * DESCRIPTION: COMPANION token in companion object with property member
 */
// TESTCASE NUMBER: 1

class PropCompanion76 {
    companion object {
        const val TOKEN = "kw-76-76-3"
    }
}

fun box(): String {
    val expected = "kw-76-76-3"
    val result = PropCompanion76.TOKEN
    if (result != expected) return "NOK"
    return "OK"
}
