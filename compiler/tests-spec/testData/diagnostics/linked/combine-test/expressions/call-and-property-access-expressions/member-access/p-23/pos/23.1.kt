// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST -DEBUG_INFO_CONSTANT
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 23 -> sentence 23
 *                type-inference, smart-casts -> paragraph 23 -> sentence 23
 *                expressions, elvis-operator-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: smart cast after null check then safe call elvis
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun case1(box: Box?): Int {
    if (box != null) {
        checkSubtype<Int>(box.value)
        return box.value
    }
    checkSubtype<Int>(box?.value ?: 0)
    return box?.value ?: 0
}
