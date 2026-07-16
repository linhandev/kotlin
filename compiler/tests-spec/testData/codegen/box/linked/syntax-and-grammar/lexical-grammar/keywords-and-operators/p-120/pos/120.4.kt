// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 120 -> sentence 120
 * NUMBER: 4
 * DESCRIPTION: OPEN token in open class implementing interface
 */
// TESTCASE NUMBER: 1
interface OpenIface120 {
    fun label120(): String
}

open class OpenImpl120 : OpenIface120 {
    override fun label120(): String = "codegen-120-4"
}

fun box(): String = if (OpenImpl120().label120() == "codegen-120-4") "OK" else "NOK"
