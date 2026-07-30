// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 105 -> sentence 105
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 105 -> sentence 105
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 105 -> sentence 105
 * NUMBER: 1
 * DESCRIPTION: init block runs before secondary constructor body after this() delegation in class declaration
 */

// TESTCASE NUMBER: 1
class Order() {
    val steps = mutableListOf<String>()

    init {
        steps += "init"
    }

    constructor(tag: String) : this() {
        steps += "sec"
    }
}

fun stepsViaSecondary(): List<String> = Order("t").steps

fun stepsViaSecondaryOther(): List<String> = Order("u").steps

fun stepsViaPrimary(): List<String> = Order().steps

fun box(): String {
    val secondary = stepsViaSecondary()
    if (secondary.size != 2) return "NOK: secondary size"
    if (secondary[0] != "init") return "NOK: secondary init"
    if (secondary[1] != "sec") return "NOK: secondary sec"
    val secondaryOther = stepsViaSecondaryOther()
    if (secondaryOther.size != 2) return "NOK: secondary other size"
    if (secondaryOther[0] != "init") return "NOK: secondary other init"
    if (secondaryOther[1] != "sec") return "NOK: secondary other sec"
    val primary = stepsViaPrimary()
    if (primary.size != 1) return "NOK: primary size"
    if (primary[0] != "init") return "NOK: primary init"
    return "OK"
}
