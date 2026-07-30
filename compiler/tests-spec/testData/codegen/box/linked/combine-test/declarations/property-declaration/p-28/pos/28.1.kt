/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: inline val getter
 */

// TESTCASE NUMBER: 1
class Box {
    inline val x: Int get() = 42
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
