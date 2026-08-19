// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 27 -> sentence 27
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: enum constant set is closed and known at compile time for exhaustiveness
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { R, G }

fun case_1(c: Color) {
    checkSubtype<String>(when (c) {
        Color.R -> "r"
        Color.G -> "g"
    })
}
