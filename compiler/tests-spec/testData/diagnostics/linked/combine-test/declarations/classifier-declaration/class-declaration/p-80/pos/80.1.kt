// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 80 -> sentence 80
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 80 -> sentence 80
 *                declarations, property-declaration -> paragraph 80 -> sentence 80
 * NUMBER: 1
 * DESCRIPTION: init block assigns property using primary constructor param
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Demo(val x: Int) {
    val y: Int
    init { y = x + 1 }
}

fun case1() {
    checkSubtype<Int>(Demo(1).y)
}

