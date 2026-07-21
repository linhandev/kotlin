// FIR_IDENTICAL
// LANGUAGE: +InlineClasses
// DIAGNOSTICS: -UNUSED_VARIABLE -INLINE_CLASS_DEPRECATED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: reference equality operators are forbidden on value classes
 */

// TESTCASE NUMBER: 1
inline class Foo(val x: Int)
inline class Bar(val y: String)

fun test(f1: Foo, f2: Foo, b: Bar) {
    val a1 = <!FORBIDDEN_IDENTITY_EQUALS!>f1 === f2<!>
    val a2 = <!FORBIDDEN_IDENTITY_EQUALS!>f1 !== f2<!>
    val a3 = <!EQUALITY_NOT_APPLICABLE, FORBIDDEN_IDENTITY_EQUALS!>f1 === b<!>
}
