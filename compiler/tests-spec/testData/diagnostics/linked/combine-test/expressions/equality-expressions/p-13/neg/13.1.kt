// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 13 -> sentence 13
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: Int equals String reports EQUALITY_NOT_APPLICABLE
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = <!EQUALITY_NOT_APPLICABLE!>1 == "a"<!>
