// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 63 -> sentence 63
 * NUMBER: 4
 * DESCRIPTION: SETPARAM token on override mutable property before setter
 */
// TESTCASE NUMBER: 1

open class BaseSetparam63 {
    open var label: String = "base"
        set(value) {
            field = value
        }
}

class DerivedSetparam63 : BaseSetparam63() {
    @setparam:Suppress("UNUSED_PARAMETER")
    override var label: String = "NOK"
        set(value) {
            field = value
        }
}

fun box(): String {
    val expected = "setparam-63-4"
    val derived = DerivedSetparam63()
    derived.label = expected
    if (derived.label != expected) return "NOK"
    return "OK"
}
