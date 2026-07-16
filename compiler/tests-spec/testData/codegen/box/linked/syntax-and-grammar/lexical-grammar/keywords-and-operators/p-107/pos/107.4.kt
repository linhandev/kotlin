// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 107 -> sentence 107
 * NUMBER: 4
 * DESCRIPTION: SEALED token in nested sealed class hierarchy
 */
sealed class Container107 {
    sealed class Box107 : Container107() {
        data class Item107(val token: String) : Box107()
    }
}

// TESTCASE NUMBER: 1
fun box(): String = when (val value: Container107 = Container107.Box107.Item107("OK")) {
    is Container107.Box107.Item107 -> value.token
    else -> "NOK"
}
