// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 108 -> sentence 108
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 108 -> sentence 108
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 108 -> sentence 108
 *                declarations, declarations-with-type-parameters -> paragraph 108 -> sentence 108
 * NUMBER: 1
 * DESCRIPTION: generic class secondary constructor delegates to primary constructor in class declaration
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T) {
    constructor(x: T, marker: Boolean) : this(x)
}

fun intViaSecondary(): Int = Box(1, true).v

fun stringViaSecondary(): String = Box("hi", true).v

fun doubleViaPrimary(): Double = Box(3.5).v

fun box(): String {
    val intSecondary = intViaSecondary()
    if (intSecondary != 1) return "NOK: int secondary"
    val stringSecondary = stringViaSecondary()
    if (stringSecondary != "hi") return "NOK: string secondary"
    val doublePrimary = doubleViaPrimary()
    if (doublePrimary != 3.5) return "NOK: double primary"
    return "OK"
}
