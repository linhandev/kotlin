// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 16 -> sentence 16
 *                declarations, function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: extension on nullable receiver infers non-null Boolean result regardless of receiver nullability
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun String?.isNotNullOrEmpty(): Boolean = this?.isNotEmpty() == true

fun case1(s: String?) {
    checkSubtype<Boolean>(s.isNotNullOrEmpty())
}
