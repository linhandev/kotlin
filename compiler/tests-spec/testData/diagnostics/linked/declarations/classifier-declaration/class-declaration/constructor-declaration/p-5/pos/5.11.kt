// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 4 -> sentence 4
 * declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * SECONDARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 11
 * DESCRIPTION: nested class var constructor properties declare mutable members with various parameter types including bounded generics
 */

// TESTCASE NUMBER: 1
class Case1 {
    class A(var x: Any?)
    class B(var x: Any)
    class C(var c: () -> Any)
    class D(var e: Enum<*>)
    class E(var n: Nothing)
    class F<T>(var t: T)
}

// TESTCASE NUMBER: 2
class Case2<T>() {
    class A<T : CharSequence>(var e: T)
    class B<T : java.util.AbstractCollection<Int>>(var e: T)
    class C<T : java.lang.Exception>(var e: T)
    class D<T : Enum<*>>(var e: T)
    class F<T>(var t: T)
}

