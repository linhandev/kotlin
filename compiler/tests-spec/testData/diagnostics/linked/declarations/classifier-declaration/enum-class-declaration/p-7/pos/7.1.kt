// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: enum entry overrides abstract member in enum class body
 */

// TESTCASE NUMBER: 1
enum class Direction(val symbol: Char) {
    UP('^') {
        override val opposite: Direction
            get() = DOWN
    },
    DOWN('v') {
        override val opposite: Direction
            get() = UP
    };

    abstract val opposite: Direction
}

fun case1() {
    val up = Direction.UP
    val opp = up.opposite
}
