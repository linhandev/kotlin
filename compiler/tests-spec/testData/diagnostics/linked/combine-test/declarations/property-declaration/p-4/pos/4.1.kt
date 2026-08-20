// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: custom setter doubles assigned value
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
        set(value) {
            field = value * 2
        }
}

fun case_1() {
    checkSubtype<Int>(Box().apply { x = 10 }.x)
}
