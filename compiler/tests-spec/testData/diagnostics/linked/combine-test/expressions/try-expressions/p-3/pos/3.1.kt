// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: try and catch returning unrelated types infer common supertype Any
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = true
    checkSubtype<Any>(try {
        if (flag) 1 else throw Exception()
    } catch (e: Exception) {
        "error"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = false
    checkSubtype<Any>(try {
        if (flag) 1 else throw Exception()
    } catch (e: Exception) {
        "error"
    })
}
