// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 120 -> sentence 120
 * NUMBER: 1
 * DESCRIPTION: OPEN token in open class with open function
 */
// TESTCASE NUMBER: 1
open class OpenBase120 {
    open fun value120(): String = "codegen-120-1"
}

class OpenChild120 : OpenBase120() {
    override fun value120(): String = "codegen-120-1"
}

fun box(): String = if (OpenChild120().value120() == "codegen-120-1") "OK" else "NOK"
