// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: SUPER token in override function calling super.method()
 */
// TESTCASE NUMBER: 1

open class BaseSuper79 {
    open fun value(): Int = 1
}

class DerivedSuper79 : BaseSuper79() {
    override fun value(): Int = super.value() + 10
}

fun box(): String {
    return if (DerivedSuper79().value() == 11) "OK" else "NOK"
}
