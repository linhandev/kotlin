// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 11 -> sentence 11
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: function-type property can be passed as a named argument
 */

// TESTCASE NUMBER: 1
class Ops {
    val inc: (Int) -> Int = { it + 1 }
}

fun apply(op: (Int) -> Int, x: Int): Int = op(x)

fun test(o: Ops): Int = apply(o.inc, 1)

fun box(): String {
    if (test(Ops()) != 2) return "NOK"
    return "OK"
}
