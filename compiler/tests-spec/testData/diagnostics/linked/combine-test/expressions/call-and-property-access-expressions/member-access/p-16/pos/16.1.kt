// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 *                expressions, cast-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: as? String then ?.length infers Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(obj: Any?) {
    checkSubtype<Int?>((obj as? String)?.length)
}
