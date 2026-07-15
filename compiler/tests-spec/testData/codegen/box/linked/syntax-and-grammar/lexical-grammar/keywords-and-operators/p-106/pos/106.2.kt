// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 106 -> sentence 106
 * NUMBER: 2
 * DESCRIPTION: ENUM token in enum class with constructor properties
 */
enum class Status106(val label: String) {
    ACTIVE("OK"), INACTIVE("NOK")
}

// TESTCASE NUMBER: 1
fun box(): String = Status106.ACTIVE.label
