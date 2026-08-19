// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 20 -> sentence 20
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: enum entries size is independent of when exhaustiveness
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1() {
    checkSubtype<Int>(E.entries.size + when (E.A) {
        E.A -> 1
        E.B -> 2
    })
}
