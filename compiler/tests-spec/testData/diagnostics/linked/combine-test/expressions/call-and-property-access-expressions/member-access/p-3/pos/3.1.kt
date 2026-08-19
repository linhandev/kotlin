// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: explicit Int? local matches safe call result type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun test(box: Box?): Int? {
    val r: Int? = box?.value
    return r
}

fun case1(box: Box?) {
    checkSubtype<Int?>(test(box))
    val r: Int? = box?.value
    checkSubtype<Int?>(r)
}
