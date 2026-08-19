// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 19 -> sentence 19
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: constructor trailing lambda type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Builder(val setup: () -> Unit)

fun case1() {
    checkSubtype<Builder>(Builder { })
}
