// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: enum members are accessible inside when branches
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E(val code: Int) {
    A(1),
    B(2)
}

fun case_1(e: E) {
    checkSubtype<Int>(when (e) {
        E.A -> e.code
        E.B -> e.code
    })
}
