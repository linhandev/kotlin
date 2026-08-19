// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: destructuring lambda works with trailing lambda syntax
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun applyPair(p: Pair<Int, Int>, block: (Pair<Int, Int>) -> Int): Int = block(p)

fun case_1() {
    val r = applyPair(1 to 2) { (x, y) -> x + y }
    checkSubtype<Int>(r)
}
