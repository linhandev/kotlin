/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: top-level val property
 */

// TESTCASE NUMBER: 1
val globalX = 42

fun test() = globalX

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
