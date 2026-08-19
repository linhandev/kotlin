// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                expressions, elvis-operator-expressions -> paragraph 21 -> sentence 21
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 21 -> sentence 21
 *                expressions, jump-expressions, return-expressions -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return is Nothing inside try expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    val x: String? = "hi"
    val n = try {
        x?.length ?: return -1
    } catch (e: Exception) {
        0
    }
    checkSubtype<Int>(n)
    return n
}

// TESTCASE NUMBER: 2
fun case2(): Int {
    val x: String? = null
    val n = try {
        x?.length ?: return -1
    } catch (e: Exception) {
        0
    }
    checkSubtype<Int>(n)
    return n
}
