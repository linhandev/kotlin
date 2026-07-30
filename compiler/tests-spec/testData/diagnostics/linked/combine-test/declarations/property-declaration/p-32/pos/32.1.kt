// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: property assigned in init block
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int
    init {
        x = 42
    }
}

fun case_1() {
    checkSubtype<Int>(Box().x)
}
