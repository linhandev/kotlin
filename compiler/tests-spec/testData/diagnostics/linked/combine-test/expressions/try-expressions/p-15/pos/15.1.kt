// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 15 -> sentence 15
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 15 -> sentence 15
 *                type-system, introduction-1 -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: nullable try branch with Nothing catch remains String?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = true
    checkSubtype<String?>(try {
        if (flag) "ok" else null
    } catch (e: Exception) {
        throw e
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = false
    checkSubtype<String?>(try {
        if (flag) "ok" else null
    } catch (e: Exception) {
        throw e
    })
}
