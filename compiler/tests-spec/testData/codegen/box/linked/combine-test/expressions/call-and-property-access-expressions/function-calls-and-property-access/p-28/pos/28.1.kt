// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 28 -> sentence 28
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: apply trailing lambda resolves receiver property access inside lambda body
 */

// TESTCASE NUMBER: 1
class Box(var v: Int)

fun test(b: Box): Box = b.apply { v++ }

fun box(): String {
    val b = Box(1)
    if (test(b).v != 2) return "NOK"
    if (b.v != 2) return "NOK"
    return "OK"
}
