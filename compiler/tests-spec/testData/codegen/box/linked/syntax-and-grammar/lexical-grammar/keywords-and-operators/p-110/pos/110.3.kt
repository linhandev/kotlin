// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 110 -> sentence 110
 * NUMBER: 3
 * DESCRIPTION: INNER token in inner class with qualified this@Outer
 */
// TESTCASE NUMBER: 1
class Shell110 {
    val label: String = "codegen-110-3"
    inner class View110 {
        fun read(): String = this@Shell110.label
    }
}

fun box(): String = if (Shell110().View110().read() == "codegen-110-3") "OK" else "NOK"
