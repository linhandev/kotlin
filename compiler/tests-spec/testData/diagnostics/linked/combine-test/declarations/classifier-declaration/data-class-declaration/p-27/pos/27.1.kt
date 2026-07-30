// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: Pair/Triple destructuring uses the same componentN mechanism
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val (a, b, c) = Triple(1, 2, 3)
    checkSubtype<Int>(a + b + c)
}
