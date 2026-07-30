// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 9 -> sentence 9
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: function-type property invocation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Handler {
    val work: (Int) -> Int = { it * 2 }
}

fun case1(h: Handler) {
    checkSubtype<Int>(h.work(3))
}
