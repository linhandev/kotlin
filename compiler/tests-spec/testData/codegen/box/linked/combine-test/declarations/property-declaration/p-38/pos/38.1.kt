/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: const val String property
 */

// TESTCASE NUMBER: 1
const val name = "hello"

fun test() = name

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}
