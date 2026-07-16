// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 4 -> sentence 4
 * declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * type-system, type-contexts-and-scopes, inner-and-nested-type-contexts -> paragraph 1 -> sentence 1
 * SECONDARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: nested class var constructor property cannot reference enclosing generic class type parameter
 */

// TESTCASE NUMBER: 1
class Case1<T>() {
    class A(var t: <!UNRESOLVED_REFERENCE!>T<!>)
    class B(var x: List<<!UNRESOLVED_REFERENCE!>T<!>>)
    class C(var c: () -> <!UNRESOLVED_REFERENCE!>T<!>)
    class E(var n: Nothing, var t: <!UNRESOLVED_REFERENCE!>T<!>)
}