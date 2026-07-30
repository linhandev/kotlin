// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 147 -> sentence 147
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 147 -> sentence 147
 *                inheritance, inheriting -> paragraph 147 -> sentence 147
 * NUMBER: 1
 * DESCRIPTION: subclass must delegate to superclass constructor in the supertype list; bare class supertype is not initialized in class declaration
 */

// TESTCASE NUMBER: 1
open class Parent(val x: Int)

class Child : <!SUPERTYPE_NOT_INITIALIZED!>Parent<!>

// TESTCASE NUMBER: 2
open class Sized(val n: Int)

class Named(name: String) : <!SUPERTYPE_NOT_INITIALIZED!>Sized<!>

// TESTCASE NUMBER: 3
interface Marker

open class Base(val id: Int)

class Mixed : <!SUPERTYPE_NOT_INITIALIZED!>Base<!>, Marker
