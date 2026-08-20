// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 164 -> sentence 164
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 164 -> sentence 164
 *                inheritance, inheriting -> paragraph 164 -> sentence 164
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 164 -> sentence 164
 * NUMBER: 1
 * DESCRIPTION: sealed class cannot be inherited from disallowed scopes even when constructor delegation syntax is otherwise valid in class declaration
 */

// FILE: sealedExpr.kt
package sealedcombo74

sealed class Expr(val tag: Int)

sealed class Shape(val id: Int)

// FILE: otherPackage.kt
package othercombo74

import sealedcombo74.Expr
import sealedcombo74.Shape

// TESTCASE NUMBER: 1
class Bad(tag: Int) : <!SEALED_INHERITOR_IN_DIFFERENT_PACKAGE!>Expr<!>(tag)

// TESTCASE NUMBER: 2
class BadShape(id: Int, val extra: String) : <!SEALED_INHERITOR_IN_DIFFERENT_PACKAGE!>Shape<!>(id)

// FILE: localAndAnon.kt
package sealedcombo74

// TESTCASE NUMBER: 3
fun illegalLocal(tag: Int) {
    class Local(t: Int) : <!SEALED_SUPERTYPE!>Expr<!>(t)
    val anon = object : <!SEALED_SUPERTYPE!>Shape<!>(tag) {}
}
