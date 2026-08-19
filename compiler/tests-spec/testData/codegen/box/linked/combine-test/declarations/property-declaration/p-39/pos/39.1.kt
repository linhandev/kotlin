// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: @JvmField property
 */

// TESTCASE NUMBER: 1
class Box {
    @JvmField
    val x: Int = 42
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
