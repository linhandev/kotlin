// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 16 -> sentence 16
 *                type-system, type-kinds, nullable-types -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: Java System.getProperty platform type can be assigned to String? type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<String?>(System.getProperty("any.key.56216"))
}
