// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 120 -> sentence 120
 * NUMBER: 2
 * DESCRIPTION: OPEN token in open property declaration
 */
// TESTCASE NUMBER: 1
open class OpenProp120 {
    open val token120: String = "codegen-120-2"
}

class OpenPropChild120 : OpenProp120() {
    override val token120: String = "codegen-120-2"
}

fun box(): String = if (OpenPropChild120().token120 == "codegen-120-2") "OK" else "NOK"
