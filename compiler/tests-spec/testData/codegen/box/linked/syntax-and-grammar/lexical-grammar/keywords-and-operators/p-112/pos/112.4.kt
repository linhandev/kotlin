// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 112 -> sentence 112
 * NUMBER: 4
 * DESCRIPTION: OPERATOR token in get index operator function
 */
// TESTCASE NUMBER: 1
class Table112(private val rows: Array<String>) {
    operator fun get(index: Int): String = rows[index]
}

fun box(): String = if (Table112(arrayOf("NOK", "codegen-112-4"))[1] == "codegen-112-4") "OK" else "NOK"
