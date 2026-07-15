// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 70 -> sentence 70
 * NUMBER: 1
 * DESCRIPTION: OBJECT token in object declaration singleton
 */
// TESTCASE NUMBER: 1

object Singleton70 {
    fun value(): String = "kw-70-70-1"
}

fun box(): String {
    val expected = "kw-70-70-1"
    val result = Singleton70.value()
    if (result != expected) return "NOK"
    return "OK"
}
