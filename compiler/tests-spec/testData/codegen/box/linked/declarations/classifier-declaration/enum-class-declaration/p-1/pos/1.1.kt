// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: enum entry name, ordinal, and compareTo work correctly at runtime
 */

// TESTCASE NUMBER: 1
enum class Rank {
    LOW,
    HIGH
}

fun box(): String {
    val low = Rank.LOW
    val high = Rank.HIGH
    return if (low.name == "LOW" && low.ordinal == 0 && low.compareTo(high) < 0) "OK" else "NOK"
}
