// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: try expression as function call argument participates in type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun accept(n: Number) = n

fun case1() {
    checkSubtype<Number>(accept(try {
        1
    } catch (e: Exception) {
        2.0
    }))
}
