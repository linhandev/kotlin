// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: enum class entries expose name and ordinal at runtime
 */

// TESTCASE NUMBER: 1
enum class Color {
    RED,
    GREEN
}

fun box(): String {
    return if (Color.RED.name == "RED" && Color.GREEN.ordinal == 1) "OK" else "NOK"
}
