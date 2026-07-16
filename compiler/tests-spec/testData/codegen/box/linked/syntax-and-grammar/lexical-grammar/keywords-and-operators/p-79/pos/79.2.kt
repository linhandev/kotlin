// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 79 -> sentence 79
 * NUMBER: 2
 * DESCRIPTION: SUPER token in property override accessing super.property
 */
// TESTCASE NUMBER: 1

open class BaseProp79 {
    open val token: String = "kw-79-79-2"
}

class DerivedProp79 : BaseProp79() {
    fun read(): String = super.token
}

fun box(): String {
    val expected = "kw-79-79-2"
    val result = DerivedProp79().read()
    if (result != expected) return "NOK"
    return "OK"
}
