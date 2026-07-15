// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 117 -> sentence 117
 * NUMBER: 2
 * DESCRIPTION: OVERRIDE token in override property declaration
 */
// TESTCASE NUMBER: 1
open class BaseProp117 {
    open val token117: String = "NOK"
}

class ChildProp117 : BaseProp117() {
    override val token117: String = "codegen-117-2"
}

fun box(): String = if (ChildProp117().token117 == "codegen-117-2") "OK" else "NOK"
