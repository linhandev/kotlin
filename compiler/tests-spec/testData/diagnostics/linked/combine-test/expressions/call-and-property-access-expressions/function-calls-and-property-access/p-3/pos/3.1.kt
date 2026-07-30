// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 3 -> sentence 3
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: omitted call parentheses with single trailing lambda type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun runBlock(block: () -> Unit) {
    block()
}

fun case1() {
    runBlock { }
    checkSubtype<Unit>(Unit)
}
