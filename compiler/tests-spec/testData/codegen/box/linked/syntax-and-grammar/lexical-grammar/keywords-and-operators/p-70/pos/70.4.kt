// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 70 -> sentence 70
 * NUMBER: 4
 * DESCRIPTION: OBJECT token in object implementing interface
 */
// TESTCASE NUMBER: 1

interface Token70 {
    fun value(): String
}

object ObjectIface70 : Token70 {
    override fun value(): String = "kw-70-70-4"
}

fun box(): String {
    val expected = "kw-70-70-4"
    val result = ObjectIface70.value()
    if (result != expected) return "NOK"
    return "OK"
}
