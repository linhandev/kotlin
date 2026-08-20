// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 17 -> sentence 17
 *                type-system, type-kinds, nullable-types -> paragraph 17 -> sentence 17
 *                expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: platform type can be assigned to Kotlin String when null is handled with Elvis type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<String>(System.getProperty("path") ?: "")
}
