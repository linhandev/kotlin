// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 78 -> sentence 78
 *                declarations, property-declaration -> paragraph 78 -> sentence 78
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: enum primary constructor parameter is property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color(val rgb: Int) { RED(0xFF0000) }

fun case1() {
    checkSubtype<Int>(Color.RED.rgb)
}

