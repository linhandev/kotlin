// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 23 -> sentence 23
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: sealed inheritor in a different package is not allowed
 */

// FILE: a.kt
package sealedpkg40

sealed class Expr

// FILE: b.kt
package otherpkg40

import sealedpkg40.Expr

// TESTCASE NUMBER: 1
class Hack : <!SEALED_INHERITOR_IN_DIFFERENT_PACKAGE!>Expr<!>()
