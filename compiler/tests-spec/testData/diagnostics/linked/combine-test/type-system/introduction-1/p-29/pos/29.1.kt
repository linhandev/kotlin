// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 29 -> sentence 29
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: casting Array<Any?> to Array<String> may fail at runtime type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Array<String>>(arrayOf<Any?>(1) <!UNCHECKED_CAST!>as Array<String><!>)
}
