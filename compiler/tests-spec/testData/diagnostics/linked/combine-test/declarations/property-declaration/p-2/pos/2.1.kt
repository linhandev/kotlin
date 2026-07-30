// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: mutable var property declaration has type Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
}

fun case_1() {
    val b = Box()
    b.x = 42
    checkSubtype<Int>(b.x)
}
