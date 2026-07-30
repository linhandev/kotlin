// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 9 -> sentence 9
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: function-type property is accessed and invoked as a regular call
 */

// TESTCASE NUMBER: 1
class Handler {
    val work: (Int) -> Int = { it * 2 }
}

fun test(h: Handler): Int = h.work(3)

fun box(): String {
    if (test(Handler()) != 6) return "NOK"
    return "OK"
}
