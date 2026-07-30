// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 26 -> sentence 26
 *                expressions, object-literals, functional-interface-lambda-literals -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: functional interface call accepts trailing lambda argument
 */

// TESTCASE NUMBER: 1
var ran = false

fun interface Action {
    fun run()
}

fun exec(a: Action) {
    a.run()
}

fun test() {
    exec { ran = true }
}

fun box(): String {
    ran = false
    test()
    if (!ran) return "NOK"
    return "OK"
}
