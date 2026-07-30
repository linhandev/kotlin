// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: inline val getter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    inline val x: Int get() = 42
}

fun case_1() {
    checkSubtype<Int>(Box().x)
}
