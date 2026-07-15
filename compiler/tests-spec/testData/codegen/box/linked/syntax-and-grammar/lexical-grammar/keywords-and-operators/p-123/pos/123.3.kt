// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 123 -> sentence 123
 * NUMBER: 3
 * DESCRIPTION: VARARG token in vararg with spread operator call
 */
fun spread123(prefix: String, vararg items: String): String {
    return prefix + items.joinToString("")
}

// TESTCASE NUMBER: 1
fun box(): String = spread123("", *arrayOf("O", "K"))
