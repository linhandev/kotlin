// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: lateinit property assignment
 */

// TESTCASE NUMBER: 1
class Box {
    lateinit var x: String
}

fun test() = Box().apply { x = "hello" }.x

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}
