// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 118 -> sentence 118
 * NUMBER: 3
 * DESCRIPTION: ABSTRACT token in abstract override function
 */
// TESTCASE NUMBER: 1
interface Named118 {
    fun name118(): String
}

abstract class NamedBase118 : Named118 {
    abstract override fun name118(): String
}

class NamedImpl118 : NamedBase118() {
    override fun name118(): String = "codegen-118-3"
}

fun box(): String = if (NamedImpl118().name118() == "codegen-118-3") "OK" else "NOK"
