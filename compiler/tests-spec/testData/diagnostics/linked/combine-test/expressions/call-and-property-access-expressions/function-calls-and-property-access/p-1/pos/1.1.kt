// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: trailing lambda as last function-type parameter type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun applyOp(x: Int, op: (Int) -> Int): Int = op(x)

fun case1() {
    checkSubtype<Int>(applyOp(1) { it + 1 })
}
