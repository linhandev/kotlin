// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 58 -> sentence 58
 * NUMBER: 4
 * DESCRIPTION: PROPERTY token on abstract interface property declaration
 */
// TESTCASE NUMBER: 1

interface PropertyIface58 {
    @property:Suppress("UNUSED")
    val title: String
}

class PropertyIfaceImpl58 : PropertyIface58 {
    override val title = "kw-58-58-4"
}

fun box(): String {
    val expected = "kw-58-58-4"
    val result = PropertyIfaceImpl58().title
    if (result != expected) return "NOK"
    return "OK"
}
