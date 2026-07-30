// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: destructuring lambda works with trailing lambda syntax
 */

// TESTCASE NUMBER: 1
fun applyPair(p: Pair<Int, Int>, block: (Pair<Int, Int>) -> Int): Int = block(p)

fun test(): Int = applyPair(1 to 2) { (x, y) -> x + y }

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
