// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: backing field setter only stores positive values
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
        set(value) {
            if (value > 0) field = value
        }
}

fun test() = Box().apply { x = -5 }.x

fun box(): String {
    if (test() != 0) return "NOK"
    return "OK"
}
