// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 11 -> sentence 11
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 11 -> sentence 11
 *                type-system, introduction-1 -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: nullable enum when with explicit null branch is exhaustive
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E?) {
    checkSubtype<String>(when (e) {
        E.A -> "a"
        E.B -> "b"
        null -> "n"
    })
}
