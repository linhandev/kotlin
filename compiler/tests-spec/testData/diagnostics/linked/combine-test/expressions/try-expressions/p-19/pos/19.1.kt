// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 *                expressions, elvis-operator-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: try block with safe call and Elvis providing default Int type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<Int>(try {
        x?.length ?: -1
    } catch (e: Exception) {
        -2
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<Int>(try {
        x?.length ?: -1
    } catch (e: Exception) {
        -2
    })
}
