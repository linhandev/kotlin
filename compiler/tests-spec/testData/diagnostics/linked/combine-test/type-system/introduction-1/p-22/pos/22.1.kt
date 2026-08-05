// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 22 -> sentence 22
 *                type-inference, introduction-1 -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: Java Arrays.asList is inferred as a List with platform element types type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<String>(java.util.Arrays.asList("a", "b").first())
}
