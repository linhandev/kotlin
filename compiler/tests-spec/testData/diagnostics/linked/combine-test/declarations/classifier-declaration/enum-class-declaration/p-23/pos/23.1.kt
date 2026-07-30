// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -REDUNDANT_ELSE_IN_WHEN
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 23 -> sentence 23
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: redundant else after exhaustive enum when is still allowed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E) {
    checkSubtype<Int>(when (e) {
        E.A -> 1
        E.B -> 2
        else -> 0
    })
}
