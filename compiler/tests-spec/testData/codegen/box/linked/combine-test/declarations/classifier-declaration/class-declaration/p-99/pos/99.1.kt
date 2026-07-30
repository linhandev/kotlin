// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 99 -> sentence 99
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 99 -> sentence 99
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 99 -> sentence 99
 *                inheritance, inheriting -> paragraph 99 -> sentence 99
 * NUMBER: 1
 * DESCRIPTION: subclass secondary constructor delegates to class primary constructor via this() before superclass initialization in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child(val y: Int) : Base(0) {
    constructor(x: Int, y: Int) : this(y)
}

fun viaSecondary(): Child = Child(1, 2)

fun viaSecondaryOther(): Child = Child(10, 20)

fun viaPrimary(): Child = Child(3)

fun box(): String {
    val secondary = viaSecondary()
    if (secondary.y != 2) return "NOK: secondary y"
    if (secondary.x != 0) return "NOK: secondary base x"
    val secondaryOther = viaSecondaryOther()
    if (secondaryOther.y != 20) return "NOK: secondary other y"
    if (secondaryOther.x != 0) return "NOK: secondary other base x"
    val primary = viaPrimary()
    if (primary.y != 3) return "NOK: primary y"
    if (primary.x != 0) return "NOK: primary base x"
    return "OK"
}
