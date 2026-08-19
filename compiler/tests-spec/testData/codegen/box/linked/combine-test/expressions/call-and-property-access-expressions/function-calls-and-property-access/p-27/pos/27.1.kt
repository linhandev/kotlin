// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 27 -> sentence 27
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: trailing lambda in nested call binds to the outer function parameter
 */

// TESTCASE NUMBER: 1
var innerCalled = false

fun outer(inner: (() -> Unit) -> Unit) {
    inner { innerCalled = true }
}

fun test() {
    outer { callback -> callback() }
}

fun box(): String {
    innerCalled = false
    test()
    if (!innerCalled) return "NOK"
    return "OK"
}
