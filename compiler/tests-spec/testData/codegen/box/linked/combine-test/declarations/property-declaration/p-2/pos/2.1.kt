// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: mutable var property can be declared and assigned
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
}

fun test(): Int {
    val b = Box()
    b.x = 42
    return b.x
}

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
