// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 12 -> sentence 12
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: safe call extension function infers Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box

fun Box.ext(): Int = 42

fun case1(box: Box?) {
    checkSubtype<Int?>(box?.ext())
}
