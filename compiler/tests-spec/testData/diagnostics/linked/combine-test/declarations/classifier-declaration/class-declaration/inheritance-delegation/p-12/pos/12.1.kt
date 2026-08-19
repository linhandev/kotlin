// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: MutableMap property delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val map: MutableMap<String, Int>) {
    var x: Int by map
}

fun case_1() {
    checkSubtype<Int>(Box(mutableMapOf("x" to 0)).apply { x = 42 }.x)
}
