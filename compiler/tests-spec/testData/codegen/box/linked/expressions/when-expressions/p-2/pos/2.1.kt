// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: when entries are evaluated in order of appearance
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var count = 0
    val r = when {
        true -> {
            count++
            "first"
        }
        true -> {
            count++
            "second"
        }
        else -> "else"
    }
    return if (r == "first" && count == 1) "OK" else "NOK"
}
