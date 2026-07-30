// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 16 -> sentence 16
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: non-Int index reports TYPE_MISMATCH
 */

// TESTCASE NUMBER: 1
fun test(): Int = arrayOf(1, 2)[<!TYPE_MISMATCH!>"0"<!>]
