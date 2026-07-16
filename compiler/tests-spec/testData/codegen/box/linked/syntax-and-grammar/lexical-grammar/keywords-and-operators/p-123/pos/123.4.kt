// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 123 -> sentence 123
 * NUMBER: 4
 * DESCRIPTION: VARARG token in member function vararg parameter
 */
class VarargHolder123 {
    fun format123(vararg tokens: String): String = tokens.joinToString("")
}

// TESTCASE NUMBER: 1
fun box(): String = VarargHolder123().format123("O", "K")
