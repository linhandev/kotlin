// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 13 -> sentence 13
 *                type-inference, smart-casts -> paragraph 13 -> sentence 13
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: smart cast does not apply to mutable property access in conditional expression branch
 */

class Box(var value: Any)

// TESTCASE NUMBER: 1
fun test(b: Box): Int = if (b.value is String) <!SMARTCAST_IMPOSSIBLE!>b.value<!>.length else -1
