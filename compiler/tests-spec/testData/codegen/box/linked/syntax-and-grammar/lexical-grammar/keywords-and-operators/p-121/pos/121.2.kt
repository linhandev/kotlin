// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 121 -> sentence 121
 * NUMBER: 2
 * DESCRIPTION: CONST token in object const val declaration
 */
// TESTCASE NUMBER: 1
object ConstHolder121 {
    const val TOKEN121: String = "codegen-121-2"
}

fun box(): String = if (ConstHolder121.TOKEN121 == "codegen-121-2") "OK" else "NOK"
