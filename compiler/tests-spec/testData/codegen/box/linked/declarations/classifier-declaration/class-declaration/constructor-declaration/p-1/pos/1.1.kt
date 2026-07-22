// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: secondary constructor delegates to primary constructor at runtime
 */

// TESTCASE NUMBER: 1
class Point(val x: Int, val y: Int) {
    constructor(x: Int) : this(x, 0)
}

fun box(): String {
    val p = Point(3)
    return if (p.x == 3 && p.y == 0) "OK" else "NOK"
}
