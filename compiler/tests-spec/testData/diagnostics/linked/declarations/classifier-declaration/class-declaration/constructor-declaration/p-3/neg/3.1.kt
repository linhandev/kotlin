// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: secondary constructor without delegation when primary constructor exists
 */

// TESTCASE NUMBER: 1
class A(val x: Int) {
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor()<!>
}
