// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 148 -> sentence 148
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 148 -> sentence 148
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 148 -> sentence 148
 * NUMBER: 1
 * DESCRIPTION: class is final by default and cannot appear as a class supertype in the supertype list of a class declaration
 */

// TESTCASE NUMBER: 1
class Parent

class Child : <!FINAL_SUPERTYPE!>Parent<!>()

// TESTCASE NUMBER: 2
class Sized(val n: Int)

class Big(n: Int) : <!FINAL_SUPERTYPE!>Sized<!>(n)

// TESTCASE NUMBER: 3
interface Tag

class Leaf

class Node : <!FINAL_SUPERTYPE!>Leaf<!>(), Tag
