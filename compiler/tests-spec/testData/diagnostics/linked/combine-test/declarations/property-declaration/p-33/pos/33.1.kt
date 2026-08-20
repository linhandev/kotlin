// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: default getter and setter preserve Int property type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
}

fun case_1() {
    val b = Box()
    checkSubtype<Int>(b.x)
    b.x = 42
    checkSubtype<Int>(b.x)
}
