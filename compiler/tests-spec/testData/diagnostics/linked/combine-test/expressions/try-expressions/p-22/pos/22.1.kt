// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 *                expressions, elvis-operator-expressions -> paragraph 22 -> sentence 22
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 22 -> sentence 22
 *                expressions, jump-expressions, throw-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand throw is Nothing inside try expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<Int>(try {
        x?.length ?: throw IllegalArgumentException()
    } catch (e: IllegalArgumentException) {
        -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<Int>(try {
        x?.length ?: throw IllegalArgumentException()
    } catch (e: IllegalArgumentException) {
        -1
    })
}
