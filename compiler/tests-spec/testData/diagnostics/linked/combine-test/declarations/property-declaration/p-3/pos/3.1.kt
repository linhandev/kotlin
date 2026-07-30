// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: custom getter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int get() = 42
}

fun case_1() {
    checkSubtype<Int>(Box().x)
}
