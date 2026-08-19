/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: getter return type inference
 */

// TESTCASE NUMBER: 1
class Box {
    val x get() = "hello"
}

fun test() = Box().x

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}
