// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 60 -> sentence 60
 * NUMBER: 3
 * DESCRIPTION: SET token in override property setter override set(value)
 */
// TESTCASE NUMBER: 1

open class BaseSet60 {
    open var label: String = "base"
        set(value) {
            field = value
        }
}

class DerivedSet60 : BaseSet60() {
    override var label: String = "NOK"
        set(value) {
            field = value
        }
}

fun box(): String {
    val expected = "set-60-3"
    val derived = DerivedSet60()
    derived.label = expected
    if (derived.label != expected) return "NOK"
    return "OK"
}
