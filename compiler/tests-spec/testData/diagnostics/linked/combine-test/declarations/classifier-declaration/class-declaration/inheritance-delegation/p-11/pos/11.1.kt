// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Map property delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val map: Map<String, Int>) {
    val x: Int by map
}

fun case_1() {
    checkSubtype<Int>(Box(mapOf("x" to 42)).x)
}
