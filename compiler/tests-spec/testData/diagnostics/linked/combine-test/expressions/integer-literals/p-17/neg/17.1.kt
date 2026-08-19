// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 17 -> sentence 17
 *                expressions, equality-expressions -> paragraph 17 -> sentence 17
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: Int separator literal cannot be directly compared to Long separator literal
 */

// TESTCASE NUMBER: 1
fun case1(): Boolean = <!EQUALITY_NOT_APPLICABLE!>1_000 == 1_000L<!>
