// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 30 -> sentence 30
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 30 -> sentence 30
 *                expressions, elvis-operator-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: chained safe call still nullable; cannot return as non-null Int
 */

// TESTCASE NUMBER: 1
class Outer(val inner: Inner?)
class Inner(val value: Int)

fun test(outer: Outer?): Int = <!TYPE_MISMATCH!>outer?.inner?.value<!>
