// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 4 -> sentence 4
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: when used as a statement with else covers remaining enum constants
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun case_1(c: Color) {
    when (c) {
        Color.RED -> {}
        else -> {}
    }
}
