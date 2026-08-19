// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 28 -> sentence 28
 *                expressions, elvis-operator-expressions -> paragraph 28 -> sentence 28
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: outer Elvis covers try safe-call null and catch null, infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<Int>((try {
        if (false) error("boom")
        x?.length
    } catch (e: Exception) {
        null
    }) ?: -1)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<Int>((try {
        if (false) error("boom")
        x?.length
    } catch (e: Exception) {
        null
    }) ?: -1)
}
