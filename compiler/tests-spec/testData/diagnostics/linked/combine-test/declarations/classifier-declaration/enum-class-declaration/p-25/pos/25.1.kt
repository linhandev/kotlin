// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 25 -> sentence 25
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: if-else chain can cover all enum constants equivalently to when
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E) {
    checkSubtype<Int>(if (e == E.A) 1 else if (e == E.B) 2 else error("x"))
}
