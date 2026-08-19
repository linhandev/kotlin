// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 28 -> sentence 28
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 28 -> sentence 28
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: safe call result Int? cannot be compared with Int directly via > operator, UNSAFE_OPERATOR_CALL diagnostic
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    val r = s?.length <!UNSAFE_OPERATOR_CALL!>><!> 0
}
