// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 3 -> sentence 3
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: else branch makes when on enum usable as an expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun case_1(c: Color) {
    checkSubtype<String>(when (c) {
        Color.RED -> "r"
        else -> "other"
    })
}
