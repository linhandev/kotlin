// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: try and catch returning different numeric types infer common supertype Number
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Number>(try {
        1
    } catch (e: Exception) {
        2.0
    })
}
