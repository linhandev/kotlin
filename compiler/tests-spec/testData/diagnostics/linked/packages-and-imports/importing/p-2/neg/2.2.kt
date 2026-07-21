// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: import keyword without identifier violates importHeader grammar
 */

// TESTCASE NUMBER: 1
import<!SYNTAX!><!>

fun case_1() {}
