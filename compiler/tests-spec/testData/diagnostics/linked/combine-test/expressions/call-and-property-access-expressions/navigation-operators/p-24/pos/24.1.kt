// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: safe call chained on nullable function return infers nullable Int? result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun maybeString(s: String?): String? = s

fun case1(s: String?) {
    checkSubtype<Int?>(maybeString(s)?.length)
}
