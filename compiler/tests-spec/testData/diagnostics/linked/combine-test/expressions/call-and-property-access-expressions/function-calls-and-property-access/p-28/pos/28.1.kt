// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 28 -> sentence 28
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: apply trailing lambda receiver property access type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(var v: Int)

fun case1() {
    checkSubtype<Box>(Box(1).apply { v++ })
}
