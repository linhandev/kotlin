// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: when with bound value supports type test contains and equality conditions
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Any = "hello"
    val result = when (x) {
        is Int -> "int"
        in listOf("a", "b") -> "in"
        "hello" -> "eq"
        else -> "else"
    }
    return if (result == "eq") "OK" else "NOK"
}
