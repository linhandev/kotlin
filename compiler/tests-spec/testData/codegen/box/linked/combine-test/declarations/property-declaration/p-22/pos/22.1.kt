// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: top-level var property
 */

// TESTCASE NUMBER: 1
var globalX = 0

fun test() = run { globalX = 42; globalX }

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
