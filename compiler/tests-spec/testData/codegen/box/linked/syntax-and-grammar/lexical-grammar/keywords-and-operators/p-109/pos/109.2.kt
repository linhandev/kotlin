// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 109 -> sentence 109
 * NUMBER: 2
 * DESCRIPTION: DATA token in data class with copy function
 */
data class User109(val name: String) {
    fun label(): String = name
}

// TESTCASE NUMBER: 1
fun box(): String = if (User109("codegen-109-2").copy().label() == "codegen-109-2") "OK" else "NOK"
