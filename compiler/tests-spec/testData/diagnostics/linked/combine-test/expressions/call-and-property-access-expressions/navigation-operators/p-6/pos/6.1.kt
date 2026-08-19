// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: safe call on a nullable field of a non-null receiver infers nullable result Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Outer(val inner: Inner?)
data class Inner(val x: Int)

fun case1(o: Outer) {
    checkSubtype<Int?>(o.inner?.x)
}
