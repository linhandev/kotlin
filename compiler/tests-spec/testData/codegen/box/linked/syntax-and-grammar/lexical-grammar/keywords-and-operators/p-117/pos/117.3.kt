// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 117 -> sentence 117
 * NUMBER: 3
 * DESCRIPTION: OVERRIDE token in override interface function
 */
// TESTCASE NUMBER: 1
interface Label117 {
    fun label117(): String
}

class LabelImpl117 : Label117 {
    override fun label117(): String = "codegen-117-3"
}

fun box(): String = if (LabelImpl117().label117() == "codegen-117-3") "OK" else "NOK"
