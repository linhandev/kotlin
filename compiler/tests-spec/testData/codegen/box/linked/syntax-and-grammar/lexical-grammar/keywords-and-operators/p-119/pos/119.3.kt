// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 119 -> sentence 119
 * NUMBER: 3
 * DESCRIPTION: FINAL token in final property of open class
 */
// TESTCASE NUMBER: 1
open class OpenProp119 {
    final val token119: String = "codegen-119-3"
}

class OpenPropChild119 : OpenProp119()

fun box(): String = if (OpenPropChild119().token119 == "codegen-119-3") "OK" else "NOK"
