// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: internal property invisible across modules
 */

// MODULE: lib
// FILE: lib.kt
internal val x = 42

// MODULE: main(lib)
// FILE: main.kt
// TESTCASE NUMBER: 1
fun case_1() = <!INVISIBLE_MEMBER!>x<!>
