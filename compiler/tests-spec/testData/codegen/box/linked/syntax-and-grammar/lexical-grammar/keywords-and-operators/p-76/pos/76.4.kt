// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 76 -> sentence 76
 * NUMBER: 4
 * DESCRIPTION: COMPANION token in companion object implementing interface
 */
// TESTCASE NUMBER: 1

interface CompanionIface76 {
    fun value(): String
}

class IfaceHost76 {
    companion object : CompanionIface76 {
        override fun value(): String = "kw-76-76-4"
    }
}

fun box(): String {
    val expected = "kw-76-76-4"
    val result = IfaceHost76.value()
    if (result != expected) return "NOK"
    return "OK"
}
