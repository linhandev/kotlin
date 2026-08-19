// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: String? as String infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: String?) {
    checkSubtype<String>(x as String)
}
