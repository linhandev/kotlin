// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 40 -> sentence 40
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: generic constructor can explicitly specify type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val xs = ArrayList<Int>(listOf(1, 2))
    checkSubtype<ArrayList<Int>>(xs)
}
