// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: chained safe call infers final nullable type Int? regardless of C.v being non-null Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class A(val b: B?)
data class B(val c: C?)
data class C(val v: Int)

fun case1(a: A?) {
    checkSubtype<Int?>(a?.b?.c?.v)
}
