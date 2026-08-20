// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 19 -> sentence 19
 *                scopes-and-identifiers, identifiers-and-paths -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: import of a nonexistent name reports unresolved reference
 */
// TESTCASE NUMBER: 1
import <!UNRESOLVED_REFERENCE!>not<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>exist<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>Missing56019<!>

fun case_1() = <!UNRESOLVED_REFERENCE!>Missing56019<!>()
