// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: getter accesses backing field
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 42
        get() = field * 2
}

fun case_1() {
    checkSubtype<Int>(Box().x)
}
