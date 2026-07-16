// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 102 -> sentence 102
 * NUMBER: 4
 * DESCRIPTION: PUBLIC token on interface member declaration
 */
// TESTCASE NUMBER: 1
interface PublicApi102 {
    public fun greet(): String
}

class PublicApiImpl102 : PublicApi102 {
    override fun greet(): String = "codegen-102-4"
}

fun box(): String = if (PublicApiImpl102().greet() == "codegen-102-4") "OK" else "NOK"
