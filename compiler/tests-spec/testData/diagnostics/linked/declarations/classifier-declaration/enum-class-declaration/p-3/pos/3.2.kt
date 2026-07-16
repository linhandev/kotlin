// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: enum entry may override toString
 */

// TESTCASE NUMBER: 1
enum class Color {
    RED,
    GREEN {
        override fun toString(): String = "green"
    }
}

fun case1() {
    val g = Color.GREEN.toString()
}
