// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 13 -> sentence 13
 *                type-inference, introduction-1 -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: try expression with all Nothing branches is usable at expected type Int
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    return try {
        throw Exception()
    } catch (e: Exception) {
        throw e
    }
}
