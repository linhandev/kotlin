// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 106 -> sentence 106
 * NUMBER: 1
 * DESCRIPTION: ENUM token in enum class with entries
 */
enum class Color106 {
    RED, GREEN, OK
}

// TESTCASE NUMBER: 1
fun box(): String = Color106.OK.name
