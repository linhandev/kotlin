/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: custom getter
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int get() = 42
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
