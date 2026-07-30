// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: trailing lambda can be passed as the last function-type parameter
 */

// TESTCASE NUMBER: 1
fun applyOp(x: Int, op: (Int) -> Int): Int = op(x)

fun test(): Int = applyOp(1) { it + 1 }

fun box(): String {
    if (test() != 2) return "NOK"
    if (applyOp(0) { 5 } != 5) return "NOK"
    return "OK"
}
