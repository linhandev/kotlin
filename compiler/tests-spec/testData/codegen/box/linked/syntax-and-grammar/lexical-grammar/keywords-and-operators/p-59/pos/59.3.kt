// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 59 -> sentence 59
 * NUMBER: 3
 * DESCRIPTION: GET token in override property getter override get()
 */
// TESTCASE NUMBER: 1

open class BaseGet59 {
    open val label: String get() = "base"
}

class DerivedGet59 : BaseGet59() {
    override val label: String get() = "kw-59-59-3"
}

fun box(): String {
    val expected = "kw-59-59-3"
    val result = DerivedGet59().label
    if (result != expected) return "NOK"
    return "OK"
}
