// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 63 -> sentence 63
 * NUMBER: 1
 * DESCRIPTION: SETPARAM token in use-site annotation @setparam:Suppress on mutable property
 */
// TESTCASE NUMBER: 1

class SetparamHolder63 {
    @setparam:Suppress("UNUSED_PARAMETER")
    var label: String = "NOK"
        set(value) {
            field = value
        }
}

fun box(): String {
    val expected = "setparam-63"
    val holder = SetparamHolder63()
    holder.label = expected
    if (holder.label != expected) return "NOK"
    return "OK"
}
