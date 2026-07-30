// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 7 -> sentence 7
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 *                type-inference, smart-casts -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast passed as function call argument type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun len(n: Int) = n

fun case1() {
    val x: Any = "hello"
    checkSubtype<Int>(len(if (x is String) x.length else -1))
}
