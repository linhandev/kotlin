// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 8 -> sentence 8
 *                expressions, jump-expressions, return-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: return in finally overrides try expression result type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    return try {
        checkSubtype<Int>(1)
        1
    } finally {
        return 2
    }
}
