// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 26 -> sentence 26
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: nullable class receiver member access without safe call is UNSAFE_CALL
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?): Int = box<!UNSAFE_CALL!>.<!>value
