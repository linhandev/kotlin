// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 27 -> sentence 27
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: outer trailing lambda in nested call type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(inner: (() -> Unit) -> Unit) {
    inner { }
}

fun case1() {
    outer { callback -> callback() }
    checkSubtype<Unit>(Unit)
}
