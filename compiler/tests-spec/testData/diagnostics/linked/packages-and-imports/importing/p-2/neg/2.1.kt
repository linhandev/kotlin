// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: star-import from object is disallowed and reports CANNOT_ALL_UNDER_IMPORT_FROM_SINGLETON
 */

// FILE: holder.kt
package pkg1003.neg1

object Holder1003a {
    val value = 1
}

// FILE: main.kt
package pkg1003.neg1

// TESTCASE NUMBER: 1
import pkg1003.neg1.<!CANNOT_ALL_UNDER_IMPORT_FROM_SINGLETON!>Holder1003a<!>.*

fun case_1() = <!UNRESOLVED_REFERENCE!>value<!>
