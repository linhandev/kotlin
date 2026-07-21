// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: internal declarations cannot be accessed from other modules
 */

// MODULE: libModule
// FILE: Lib.kt
package libModule

internal fun secret(): Int = 42

// MODULE: mainModule(libModule)
// FILE: Main.kt
package mainModule

import libModule.*

// TESTCASE NUMBER: 1
fun accessFromOtherModule(): Int = <!INVISIBLE_MEMBER!>secret<!>()
