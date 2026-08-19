// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 12 -> sentence 12
 *                type-inference, introduction-1 -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: try block of Nothing allows overall type to be determined by catch
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<String>(try {
        throw Exception()
    } catch (e: Exception) {
        "recovered"
    })
}
