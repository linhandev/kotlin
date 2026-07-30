// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 103 -> sentence 103
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 103 -> sentence 103
 *                declarations, classifier-declaration, interface-declaration -> paragraph 103 -> sentence 103
 * NUMBER: 1
 * DESCRIPTION: interface declaration cannot host secondary constructors (unlike class declaration)
 */

// TESTCASE NUMBER: 1
interface I {
    <!CONSTRUCTOR_IN_INTERFACE!>constructor()<!>
}

// TESTCASE NUMBER: 2
interface Named {
    val label: String
    <!CONSTRUCTOR_IN_INTERFACE!>constructor(label: String)<!>
}
