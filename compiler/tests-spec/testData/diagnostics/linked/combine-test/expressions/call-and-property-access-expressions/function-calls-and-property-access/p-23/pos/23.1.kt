// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 23 -> sentence 23
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: first function-type parameter in parentheses and second as trailing lambda type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun twice(before: () -> Unit, after: () -> Unit) {
    before()
    after()
}

fun case1() {
    twice({ }) { }
    checkSubtype<Unit>(Unit)
}
