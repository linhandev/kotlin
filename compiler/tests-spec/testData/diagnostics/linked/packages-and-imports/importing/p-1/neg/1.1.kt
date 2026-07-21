// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: top-level function from different package requires import directive and reports UNRESOLVED_REFERENCE
 */

// FILE: other.kt
package pkg1001.other

fun helper1001(): Int = 1

// FILE: main.kt
package pkg1001.main

// TESTCASE NUMBER: 1
fun case_1(): Int = <!UNRESOLVED_REFERENCE!>helper1001<!>()
