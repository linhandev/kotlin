// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 117 -> sentence 117
 * NUMBER: 1
 * DESCRIPTION: OVERRIDE token in override function declaration
 */
// TESTCASE NUMBER: 1
open class Base117 {
    open fun value117(): String = "NOK"
}

class Child117 : Base117() {
    override fun value117(): String = "codegen-117-1"
}

fun box(): String = if (Child117().value117() == "codegen-117-1") "OK" else "NOK"
