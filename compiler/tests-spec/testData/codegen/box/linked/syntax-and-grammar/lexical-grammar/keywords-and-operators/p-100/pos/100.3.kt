// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 100 -> sentence 100
 * NUMBER: 3
 * DESCRIPTION: OUT token in use-site variance List<out String>
 */
// TESTCASE NUMBER: 1
fun readFirst100(list: List<out String>): String {
    return list.first()
}

fun box(): String = if (readFirst100(listOf("codegen-100-3", "NOK")) == "codegen-100-3") "OK" else "NOK"
