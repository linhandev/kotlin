// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: enum entry overridden toString returns custom string at runtime
 */

// TESTCASE NUMBER: 1
enum class Color {
    RED,
    GREEN {
        override fun toString(): String = "green"
    }
}

fun box(): String = if (Color.GREEN.toString() == "green") "OK" else "NOK"
