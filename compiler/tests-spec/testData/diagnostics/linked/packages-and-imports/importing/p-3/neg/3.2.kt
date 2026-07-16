// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: renaming import makes original unqualified name unresolved per spec example
 */

// FILE: fooCase.kt
package pkg1004.foo

import pkg1004.foo.foo as baz

fun foo(): String = "fromFoo"

fun bar(): String = "fromBar"

// TESTCASE NUMBER: 1
fun case_1() {
    baz()
    bar()
    pkg1004.foo.foo()
    <!UNRESOLVED_REFERENCE!>foo<!>()
}
