// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: setter calls other method for coercion
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    fun validate(v: Int) = v.coerceAtLeast(0)
    var x: Int = 0
        set(value) {
            field = validate(value)
        }
}

fun case_1() {
    checkSubtype<Int>(Box().apply { x = -5 }.x)
}
