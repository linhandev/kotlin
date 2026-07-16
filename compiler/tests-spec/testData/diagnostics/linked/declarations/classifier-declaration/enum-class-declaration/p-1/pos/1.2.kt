// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: enum entry with body declaration
 */

// TESTCASE NUMBER: 1
enum class Color {
    RED {
        override fun toString() = "red"
    },
    GREEN {
        override fun toString() = "green"
    };
}

fun case1() {
    val r = Color.RED
    val g = Color.GREEN
}
