// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: enum entry opposite override at runtime
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

fun box(): String {
    return if (Direction.UP.opposite == Direction.DOWN && Direction.DOWN.opposite == Direction.UP) "OK" else "NOK"
}
