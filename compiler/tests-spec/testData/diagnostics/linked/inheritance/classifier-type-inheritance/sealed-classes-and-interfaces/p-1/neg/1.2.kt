// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: CrossPackageInheritor512 outside sealedpack512 reports SEALED_INHERITOR_IN_DIFFERENT_PACKAGE
 */

// FILE: sealedBase.kt
package sealedpack512

sealed class SealedBase512

// FILE: sealedOtherPackage.kt
// TESTCASE NUMBER: 1
package otherpack512

import sealedpack512.SealedBase512

class CrossPackageInheritor512 : <!SEALED_INHERITOR_IN_DIFFERENT_PACKAGE!>SealedBase512<!>()
