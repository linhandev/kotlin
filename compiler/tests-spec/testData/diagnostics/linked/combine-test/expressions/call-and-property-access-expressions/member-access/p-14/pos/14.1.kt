// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 *                expressions, elvis-operator-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: nested nullable safe call with elvis infers non-null Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box(val value: Int?)

fun case1(box: Box?) {
    checkSubtype<Int>(box?.value ?: 0)
}
