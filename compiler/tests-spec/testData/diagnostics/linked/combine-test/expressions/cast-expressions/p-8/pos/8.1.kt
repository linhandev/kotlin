// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: impossible as? reports CAST_NEVER_SUCCEEDS
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<String?>(1 <!CAST_NEVER_SUCCEEDS!>as?<!> String)
}
