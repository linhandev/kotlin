// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: when branch block returns the last expression value
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E) {
    checkSubtype<Int>(when (e) {
        E.A -> {
            val t = 1
            t
        }
        E.B -> 2
    })
}
