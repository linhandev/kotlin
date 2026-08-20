// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: lateinit property assignment
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    lateinit var x: String
}

fun case_1() {
    checkSubtype<String>(Box().apply { x = "hello" }.x)
}
