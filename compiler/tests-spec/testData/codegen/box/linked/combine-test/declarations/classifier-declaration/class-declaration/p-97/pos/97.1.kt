// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 97 -> sentence 97
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 97 -> sentence 97
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 97 -> sentence 97
 * NUMBER: 1
 * DESCRIPTION: no-arg primary constructor can be delegated via this() from secondary constructor in class declaration
 */

// TESTCASE NUMBER: 1
class Box() {
    var v = 0

    constructor(x: Int) : this() {
        v = x
    }
}

fun viaSecondary(): Box = Box(2)

fun viaImplicitPrimary(): Box = Box()

fun box(): String {
    val secondary = viaSecondary()
    if (secondary.v != 2) return "NOK: secondary v"
    val primary = viaImplicitPrimary()
    if (primary.v != 0) return "NOK: primary v"
    return "OK"
}
