// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 12 -> sentence 12
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 12 -> sentence 12
 *                statements, assignments -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: safe call assignment result cannot be used as an expression value, ASSIGNMENT_IN_EXPRESSION_CONTEXT diagnostic
 */

// TESTCASE NUMBER: 1
class Box(var v: Int)
fun case1(b: Box?) {
    val x = <!ASSIGNMENT_IN_EXPRESSION_CONTEXT!>b?.v = 1<!>
}
