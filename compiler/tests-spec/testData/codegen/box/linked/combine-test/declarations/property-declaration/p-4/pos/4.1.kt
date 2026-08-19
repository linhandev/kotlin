// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: custom setter doubles assigned value
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
        set(value) {
            field = value * 2
        }
}

fun test() = Box().apply { x = 10 }.x

fun box(): String {
    if (test() != 20) return "NOK"
    return "OK"
}
