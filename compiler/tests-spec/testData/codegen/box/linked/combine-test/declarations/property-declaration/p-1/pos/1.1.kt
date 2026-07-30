/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: basic property declaration
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int = 42
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
