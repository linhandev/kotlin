// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 108 -> sentence 108
 * NUMBER: 5
 * DESCRIPTION: ANNOTATION token as backtick-escaped identifier fun `annotation`
 */
// TESTCASE NUMBER: 1
fun `annotation`(): String = "kw-pos-108-5"

fun box(): String {
    val r = `annotation`()
    require(r == "kw-pos-108-5")
    return "OK"
}
