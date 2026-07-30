// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 12 -> sentence 12
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: else covers null and remaining cases for nullable enum when
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E?) {
    checkSubtype<String>(when (e) {
        E.A -> "a"
        else -> "other"
    })
}
