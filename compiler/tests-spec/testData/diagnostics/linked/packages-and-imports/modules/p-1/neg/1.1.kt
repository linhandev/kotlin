// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, modules -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: internal top-level declaration cannot be imported from another module
 */

// MODULE: libModule1005
// FILE: Lib.kt
package pkg1005.lib

internal class InternalApi1005

// MODULE: mainModule1005(libModule1005)
// FILE: Main.kt
// TESTCASE NUMBER: 1
package pkg1005.main

import pkg1005.lib.<!INVISIBLE_REFERENCE!>InternalApi1005<!>

fun case_1() {}
