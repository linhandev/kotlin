// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 3 -> sentence 3
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: call parentheses can be omitted when the only argument is a trailing lambda
 */

// TESTCASE NUMBER: 1
var executed = false

fun runBlock(block: () -> Unit) {
    block()
}

fun test() {
    runBlock { executed = true }
}

fun box(): String {
    executed = false
    test()
    if (!executed) return "NOK"
    return "OK"
}
