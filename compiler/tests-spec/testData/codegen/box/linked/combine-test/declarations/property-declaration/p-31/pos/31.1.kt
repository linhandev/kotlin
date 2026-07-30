/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: property initialization order from constructor parameter
 */

// TESTCASE NUMBER: 1
class Box(val name: String) {
    val length = name.length
}

fun test() = Box("hello").length

fun box(): String {
    if (test() != 5) return "NOK"
    return "OK"
}
