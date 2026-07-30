/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: const val Float property
 */

// TESTCASE NUMBER: 1
const val PI = 3.14f

fun test() = PI

fun box(): String {
    if (test() != 3.14f) return "NOK"
    return "OK"
}
