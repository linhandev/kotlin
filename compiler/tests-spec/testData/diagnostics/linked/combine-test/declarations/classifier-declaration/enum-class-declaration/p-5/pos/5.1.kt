// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: exhaustive enum when without else requires compatible branch types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E) {
    checkSubtype<Int>(when (e) {
        E.A -> 1
        E.B -> 2
    })
}
