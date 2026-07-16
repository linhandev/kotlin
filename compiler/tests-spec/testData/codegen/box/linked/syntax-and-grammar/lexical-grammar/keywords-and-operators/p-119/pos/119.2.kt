// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 119 -> sentence 119
 * NUMBER: 2
 * DESCRIPTION: FINAL token in final member function of open class
 */
// TESTCASE NUMBER: 1
open class OpenBase119 {
    final fun value119(): String = "codegen-119-2"
}

class OpenChild119 : OpenBase119()

fun box(): String = if (OpenChild119().value119() == "codegen-119-2") "OK" else "NOK"
