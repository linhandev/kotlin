// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 111 -> sentence 111
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 111 -> sentence 111
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 111 -> sentence 111
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 111 -> sentence 111
 * NUMBER: 1
 * DESCRIPTION: data class secondary constructor delegates to primary without adding component properties in class declaration
 */

// TESTCASE NUMBER: 1
data class Point(val x: Int, val y: Int) {
    constructor(x: Int) : this(x, 0)
}

fun viaSecondary(): Point = Point(1)

fun viaPrimary(): Point = Point(2, 3)

fun viaSecondaryOther(): Point = Point(5)

fun box(): String {
    val secondary = viaSecondary()
    if (secondary.x != 1) return "NOK: secondary x"
    if (secondary.y != 0) return "NOK: secondary y"
    if (secondary.component1() != 1) return "NOK: secondary component1"
    if (secondary.component2() != 0) return "NOK: secondary component2"
    val secondaryOther = viaSecondaryOther()
    if (secondaryOther.x != 5) return "NOK: other x"
    if (secondaryOther.y != 0) return "NOK: other y"
    val primary = viaPrimary()
    if (primary.x != 2) return "NOK: primary x"
    if (primary.y != 3) return "NOK: primary y"
    if (primary.copy(y = 4).y != 4) return "NOK: copy y"
    return "OK"
}
