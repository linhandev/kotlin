// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: inline property accessors without property-level inline field
 */

// TESTCASE NUMBER: 1
class Box {
    var storage: Int = 0
    var x: Int
        inline get() = storage
        inline set(value) {
            storage = value
        }
}

fun test() = Box().apply { x = 42 }.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
