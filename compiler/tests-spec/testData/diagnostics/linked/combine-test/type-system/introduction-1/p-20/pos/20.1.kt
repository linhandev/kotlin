// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 20 -> sentence 20
 *                expressions, not-null-assertion-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: non-null assertion on a null platform type fails with NPE type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<String>(System.getProperty("user.name")!!)
}
