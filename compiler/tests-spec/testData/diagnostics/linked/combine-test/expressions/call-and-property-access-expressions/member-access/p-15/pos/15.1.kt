// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 *                expressions, when-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: safe call in when condition infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun case1(box: Box?) {
    checkSubtype<String>(
        when {
            box?.value == 42 -> "found"
            else -> "not found"
        }
    )
}
