// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: enum when branches with incompatible return types fail
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E): Int = when (e) {
    E.A -> 1
    E.B -> <!TYPE_MISMATCH!>"x"<!>
}
