// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: enum == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case1() {
    checkSubtype<Boolean>(E.A == E.A)
}
