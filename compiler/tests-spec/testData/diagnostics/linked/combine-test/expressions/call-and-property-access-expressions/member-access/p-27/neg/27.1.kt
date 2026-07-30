// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: safe call property result is Int? and cannot assign to Int
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?): Int {
    val x: Int = <!TYPE_MISMATCH!>box?.value<!>
    return x
}
