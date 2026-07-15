// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 109 -> sentence 109
 * NUMBER: 4
 * DESCRIPTION: DATA token in data object declaration
 */
data object Token109 {
    fun value(): String = "codegen-109-4"
}

// TESTCASE NUMBER: 1
fun box(): String = if (Token109.value() == "codegen-109-4") "OK" else "NOK"
