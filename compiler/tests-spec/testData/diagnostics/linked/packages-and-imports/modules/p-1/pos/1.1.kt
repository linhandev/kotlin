// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, modules -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: internal top-level declaration is importable within the same module
 */

// FILE: internalDef.kt
package pkg1005.internal

internal class InternalHolder1005(val value: Int)

// FILE: internalUse.kt
package pkg1005.use

import pkg1005.internal.InternalHolder1005

// TESTCASE NUMBER: 1
fun case_1(): Int = InternalHolder1005(7).value
