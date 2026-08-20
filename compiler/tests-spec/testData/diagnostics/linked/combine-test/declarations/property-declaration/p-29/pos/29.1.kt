// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: inline property accessors without property-level inline field
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    var storage: Int = 0
    var x: Int
        inline get() = storage
        inline set(value) {
            storage = value
        }
}

fun case_1() {
    checkSubtype<Int>(Box().apply { x = 42 }.x)
}
