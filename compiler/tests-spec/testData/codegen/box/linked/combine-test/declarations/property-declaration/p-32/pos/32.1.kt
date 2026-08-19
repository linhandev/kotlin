/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: property assigned in init block
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int
    init {
        x = 42
    }
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
