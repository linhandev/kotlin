// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: property initialization order from constructor parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val name: String) {
    val length = name.length
}

fun case_1() {
    checkSubtype<Int>(Box("hello").length)
}
