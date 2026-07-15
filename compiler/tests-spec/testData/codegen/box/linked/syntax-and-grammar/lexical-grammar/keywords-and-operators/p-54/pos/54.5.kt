// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 54 -> sentence 54
 * NUMBER: 5
 * DESCRIPTION: THIS_AT token in this@Enclosing from anonymous object method
 */
// TESTCASE NUMBER: 1

class Enclosing {
    val token = "kw-54-54-5"
    fun read(): String {
        val holder = object {
            fun pick() = this@Enclosing.token
        }
        return holder.pick()
    }
}

fun box(): String {
    val expected = "kw-54-54-5"
    val result = Enclosing().read()
    if (result != expected) return "NOK"
    return "OK"
}
