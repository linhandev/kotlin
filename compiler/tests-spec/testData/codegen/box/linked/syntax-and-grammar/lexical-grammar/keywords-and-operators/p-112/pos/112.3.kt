// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 112 -> sentence 112
 * NUMBER: 3
 * DESCRIPTION: OPERATOR token in invoke operator function
 */
// TESTCASE NUMBER: 1
class Greeter112(private val token: String) {
    operator fun invoke(): String = token
}

fun box(): String = if (Greeter112("codegen-112-3")() == "codegen-112-3") "OK" else "NOK"
