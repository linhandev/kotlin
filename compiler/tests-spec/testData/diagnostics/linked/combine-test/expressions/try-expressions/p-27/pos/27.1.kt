// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                expressions, elvis-operator-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: try expression as Elvis left operand type inference to String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<String>((try {
        if (true) "ok" else null
    } catch (e: Exception) {
        null
    }) ?: "fallback")
}

// TESTCASE NUMBER: 2
fun case2() {
    checkSubtype<String>((try {
        if (false) "ok" else null
    } catch (e: Exception) {
        null
    }) ?: "fallback")
}
