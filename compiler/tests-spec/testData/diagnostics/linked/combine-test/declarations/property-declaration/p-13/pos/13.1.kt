// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: var property type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    var x = "hello"
}

fun case_1() {
    checkSubtype<String>(Box().apply { x = "world" }.x)
}
