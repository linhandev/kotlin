// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: comma merges multiple enum constants into one when branch
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B, C, D }

fun case_1(e: E) {
    checkSubtype<String>(when (e) {
        E.A, E.B -> "ab"
        E.C, E.D -> "cd"
    })
}
