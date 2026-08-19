// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 5 -> sentence 5
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: as? with safe call infers Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hi"
    checkSubtype<Int?>((x as? String)?.length)
}
