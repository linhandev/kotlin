// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: private top-level declaration cannot be imported from another file
 */

// FILE: lib.kt
package pkg1003.lib

private fun hidden1003(): Int = 1

// FILE: main.kt
// TESTCASE NUMBER: 1
package pkg1003.use

import pkg1003.lib.hidden1003

fun case_1() = hidden1003()
