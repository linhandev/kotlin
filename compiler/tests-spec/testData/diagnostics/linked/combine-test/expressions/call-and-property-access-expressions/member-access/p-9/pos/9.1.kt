// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 9 -> sentence 9
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: safe call assignment allowed on nullable receiver
 */

// TESTCASE NUMBER: 1

class Box(var value: Int)

fun case1(box: Box?) {
    box?.value = 42
}
