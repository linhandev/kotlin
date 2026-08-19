// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 4 -> sentence 4
 *                type-inference, introduction-1 -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: throw branch is Nothing and does not affect try expression result type Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = true
    checkSubtype<Int>(try {
        if (flag) 1 else throw Exception()
    } catch (e: Exception) {
        2
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = false
    checkSubtype<Int>(try {
        if (flag) 1 else throw Exception()
    } catch (e: Exception) {
        2
    })
}
