// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: packages-and-imports, modules -> paragraph 11 -> sentence 11
 *                declarations, declaration-visibility -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: internal top-level declaration cannot be imported across different modules
 */
// MODULE: lib56011
// FILE: Lib.kt
package pkg56011.api

internal class Token56011

// MODULE: main56011(lib56011)
// FILE: Main.kt
package pkg56011.app

// TESTCASE NUMBER: 1
import pkg56011.api.<!INVISIBLE_REFERENCE!>Token56011<!>

fun case_1() {}
