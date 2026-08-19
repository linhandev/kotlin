// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                type-inference, introduction-1 -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: catch returning non-null still allows overall nullable type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<String?>(try {
        x
    } catch (e: Exception) {
        "recovered"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<String?>(try {
        x
    } catch (e: Exception) {
        "recovered"
    })
}
