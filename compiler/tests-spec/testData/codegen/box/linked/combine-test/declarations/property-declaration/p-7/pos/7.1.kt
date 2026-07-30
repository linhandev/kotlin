/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: property without backing field derived from another property
 */

// TESTCASE NUMBER: 1
class Box(val name: String) {
    val length get() = name.length
}

fun test() = Box("hello").length

fun box(): String {
    if (test() != 5) return "NOK"
    if (Box("a").length != 1) return "NOK: short"
    return "OK"
}
