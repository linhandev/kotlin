// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 151 -> sentence 151
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 151 -> sentence 151
 * NUMBER: 1
 * DESCRIPTION: class declaration supertype list may contain at most one class; a second class reports MANY_CLASSES_IN_SUPERTYPE_LIST even when constructors are delegated
 */

// TESTCASE NUMBER: 1
open class A
open class B

class C : A(), <!MANY_CLASSES_IN_SUPERTYPE_LIST!>B<!>()

// TESTCASE NUMBER: 2
open class Left(val x: Int)
open class Right(val y: Int)

class Both(x: Int, y: Int) : Left(x), <!MANY_CLASSES_IN_SUPERTYPE_LIST!>Right<!>(y)

// TESTCASE NUMBER: 3
interface Tag

open class First
open class Second

class Mixed : First(), <!MANY_CLASSES_IN_SUPERTYPE_LIST!>Second<!>(), Tag
