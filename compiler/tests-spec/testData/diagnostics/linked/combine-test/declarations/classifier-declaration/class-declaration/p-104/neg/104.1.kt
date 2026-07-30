// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 104 -> sentence 104
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 104 -> sentence 104
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 104 -> sentence 104
 * NUMBER: 1
 * DESCRIPTION: enum class cannot use secondary constructor syntax in class declaration
 */

// TESTCASE NUMBER: 1
enum class E(val x: Int) {
    A(1);
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor()<!>
}
