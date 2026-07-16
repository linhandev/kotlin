// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: enum entry name, ordinal, toString, and compareTo work correctly at runtime
 */

// TESTCASE NUMBER: 1
enum class Phase { START, MIDDLE, END }

fun box(): String {
    val middle = Phase.MIDDLE
    return if (middle.name == "MIDDLE" && middle.ordinal == 1 && middle.toString() == "MIDDLE" && middle.compareTo(Phase.END) < 0) "OK" else "NOK"
}
