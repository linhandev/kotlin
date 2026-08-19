// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 15 -> sentence 15
 *                declarations, function-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: extension on nullable receiver infers non-null String result when Elvis provides non-null default
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun String?.orEmpty(): String = this ?: ""

fun case1(s: String?) {
    checkSubtype<String>(s.orEmpty())
}
