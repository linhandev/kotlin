// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 19 -> sentence 19
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: constructor call accepts trailing lambda for last function-type parameter
 */

// TESTCASE NUMBER: 1
var initialized = false

class Builder(val setup: () -> Unit) {
    init {
        setup()
    }
}

fun test() {
    Builder { initialized = true }
}

fun box(): String {
    initialized = false
    test()
    if (!initialized) return "NOK"
    return "OK"
}
