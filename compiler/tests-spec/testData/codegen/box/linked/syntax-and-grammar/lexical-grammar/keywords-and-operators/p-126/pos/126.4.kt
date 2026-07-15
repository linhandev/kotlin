// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 126 -> sentence 126
 * NUMBER: 4
 * DESCRIPTION: REIFIED token in member inline reified function
 */
// TESTCASE NUMBER: 1
class ReifiedHolder126 {
    inline fun <reified T> pick126(value: T): T = value
}

fun box(): String = if (ReifiedHolder126().pick126("codegen-126-4") == "codegen-126-4") "OK" else "NOK"
