// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: THIS token in member function accessing property via this
 */
// TESTCASE NUMBER: 1

class ThisMember78(private val token: String) {
    fun value(): String = this.token
}

fun box(): String {
    val expected = "this-78"
    if (ThisMember78(expected).value() != expected) return "NOK"
    return "OK"
}
