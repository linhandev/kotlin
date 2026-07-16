// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: import directive is local to its file and does not introduce entity to sibling file
 */

// FILE: lib.kt
package pkg1003.lib

fun onlyInLib1003(): Int = 1

// FILE: importer.kt
package pkg1003.use

import pkg1003.lib.onlyInLib1003

fun importedOk1003(): Int = onlyInLib1003()

// FILE: sibling.kt
package pkg1003.use

// TESTCASE NUMBER: 1
fun case_1(): Int = <!UNRESOLVED_REFERENCE!>onlyInLib1003<!>()
