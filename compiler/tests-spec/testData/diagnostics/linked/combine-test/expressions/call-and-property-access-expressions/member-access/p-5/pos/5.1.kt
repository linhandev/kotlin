// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNNECESSARY_SAFE_CALL
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: unnecessary safe call on non-null still typed as Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun case1(box: Box) {
    checkSubtype<Int?>(box?.value)
}
