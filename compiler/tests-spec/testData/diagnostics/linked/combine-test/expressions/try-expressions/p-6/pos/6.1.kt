// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 6 -> sentence 6
 *                expressions, jump-expressions, return-expressions -> paragraph 6 -> sentence 6
 *                type-inference, introduction-1 -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: catch with return is Nothing so try expression type remains Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    val x: Int = try {
        1
    } catch (e: Exception) {
        return 2
    }
    checkSubtype<Int>(x)
    return x
}
