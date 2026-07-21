// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 6
 * DESCRIPTION: private class member cannot be imported from another file
 */

// FILE: lib.kt
package pkg1003.lib

class Shell1003 {
    private fun secret1003(): Int = 1
}

// FILE: main.kt
// TESTCASE NUMBER: 1
package pkg1003.use

import pkg1003.lib.Shell1003.<!CANNOT_BE_IMPORTED!>secret1003<!>

fun case_1() = <!UNRESOLVED_REFERENCE!>secret1003<!>()
