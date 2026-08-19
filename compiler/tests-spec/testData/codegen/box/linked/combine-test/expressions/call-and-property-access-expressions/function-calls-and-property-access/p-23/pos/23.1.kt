// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 23 -> sentence 23
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: when two function-type parameters are passed, only the last one may be trailing
 */

// TESTCASE NUMBER: 1
var log = ""

fun twice(before: () -> Unit, after: () -> Unit) {
    before()
    after()
}

fun test() {
    twice({ log += "1" }) { log += "2" }
}

fun box(): String {
    log = ""
    test()
    if (log != "12") return "NOK"
    return "OK"
}
