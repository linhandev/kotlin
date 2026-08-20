// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 21 -> sentence 21
 *                overload-resolution, building-the-overload-candidate-set-ocs, call-without-an-explicit-receiver -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: two star imports of same-named top-level functions cause overload resolution ambiguity
 */
// FILE: a.kt
package pkg56021.p1

fun f56021(): Int = 1

// FILE: b.kt
package pkg56021.p2

fun f56021(): Int = 2

// FILE: main.kt
package pkg56021.app

import pkg56021.p1.*
import pkg56021.p2.*

// TESTCASE NUMBER: 1
fun case_1() = <!OVERLOAD_RESOLUTION_AMBIGUITY!>f56021<!>()
