/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: getter calls other method
 */

// TESTCASE NUMBER: 1
class Box {
    fun compute() = 42
    val x get() = compute()
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
