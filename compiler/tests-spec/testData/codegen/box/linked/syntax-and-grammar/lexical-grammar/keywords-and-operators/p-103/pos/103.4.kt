// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 103 -> sentence 103
 * NUMBER: 4
 * DESCRIPTION: PRIVATE token on class member property
 */
// TESTCASE NUMBER: 1
class PrivateMember103 {
    private val token: String = "codegen-103-4"
    fun read(): String = token
}

fun box(): String = if (PrivateMember103().read() == "codegen-103-4") "OK" else "NOK"
