// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: try and catch returning the same type infers that type for the try expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Int>(try {
        1
    } catch (e: Exception) {
        2
    })
}
