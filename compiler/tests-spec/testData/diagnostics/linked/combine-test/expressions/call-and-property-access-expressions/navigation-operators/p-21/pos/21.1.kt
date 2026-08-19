// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 21 -> sentence 21
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: safe call after safe cast infers nullable String? result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Any?) {
    checkSubtype<String?>((x as? String)?.uppercase())
}
