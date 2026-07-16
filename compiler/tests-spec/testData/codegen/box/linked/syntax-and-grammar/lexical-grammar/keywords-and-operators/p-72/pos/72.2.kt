// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 72 -> sentence 72
 * NUMBER: 2
 * DESCRIPTION: VAR token in class var property declaration
 */
// TESTCASE NUMBER: 1

class VarHolder72 {
    var label: String = "NOK"
}

fun box(): String {
    val expected = "var-72"
    val holder = VarHolder72()
    holder.label = expected
    if (holder.label != expected) return "NOK"
    return "OK"
}
