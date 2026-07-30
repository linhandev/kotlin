/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 28 -> sentence 28
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 28 -> sentence 28
 *                expressions, function-literals, lambda-literals -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: higher-order function default lambda can be replaced by trailing lambda
 */

// TESTCASE NUMBER: 1
fun runBlock(block: () -> Int = { 0 }): Int = block()

fun box(): String {
    if (runBlock { 1 } != 1) return "NOK"
    return "OK"
}
