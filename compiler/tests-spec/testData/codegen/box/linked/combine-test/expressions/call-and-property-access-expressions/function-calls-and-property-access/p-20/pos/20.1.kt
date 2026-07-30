// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 20 -> sentence 20
 *                overload-resolution, callables-and-invoke-convention -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: invoke operator call accepts trailing lambda
 */

// TESTCASE NUMBER: 1
var invoked = false

class Runner {
    operator fun invoke(block: () -> Unit) {
        block()
    }
}

fun test() {
    (Runner()) { invoked = true }
}

fun box(): String {
    invoked = false
    test()
    if (!invoked) return "NOK"
    return "OK"
}
