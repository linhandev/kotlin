// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 110 -> sentence 110
 * NUMBER: 4
 * DESCRIPTION: INNER token in nested inner class declarations
 */
// TESTCASE NUMBER: 1
class Root110 {
    inner class Middle110 {
        inner class Leaf110 {
            fun value(): String = "codegen-110-4"
        }
    }
}

fun box(): String = if (Root110().Middle110().Leaf110().value() == "codegen-110-4") "OK" else "NOK"
