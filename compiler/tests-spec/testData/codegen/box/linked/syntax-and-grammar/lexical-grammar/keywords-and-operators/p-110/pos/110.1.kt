// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 110 -> sentence 110
 * NUMBER: 1
 * DESCRIPTION: INNER token in inner class declaration
 */
// TESTCASE NUMBER: 1
class Outer110(val token: String) {
    inner class Inner110 {
        fun read(): String = token
    }
}

fun box(): String = if (Outer110("codegen-110-1").Inner110().read() == "codegen-110-1") "OK" else "NOK"
