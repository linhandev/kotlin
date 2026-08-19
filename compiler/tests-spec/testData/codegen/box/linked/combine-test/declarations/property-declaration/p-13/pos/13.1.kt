// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: var property type inference
 */

// TESTCASE NUMBER: 1
class Box {
    var x = "hello"
}

fun test() = Box().apply { x = "world" }.x

fun box(): String {
    if (test() != "world") return "NOK"
    return "OK"
}
