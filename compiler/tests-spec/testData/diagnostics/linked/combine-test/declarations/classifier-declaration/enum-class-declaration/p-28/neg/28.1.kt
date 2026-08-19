// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 28 -> sentence 28
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: adding an enum constant makes an old incomplete when non-exhaustive
 */

// TESTCASE NUMBER: 1
enum class Ver { V1, V2, V3 }

fun case_1(v: Ver): Int = <!NO_ELSE_IN_WHEN!>when<!>(v) {
    Ver.V1 -> 1
    Ver.V2 -> 2
}
