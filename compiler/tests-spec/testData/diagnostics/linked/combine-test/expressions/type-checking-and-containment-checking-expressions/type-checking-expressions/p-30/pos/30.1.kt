// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 30 -> sentence 30
 *                type-system, introduction-1 -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: unchecked cast with non-runtime-available type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(value: Any?) {
    checkSubtype<T>(value <!UNCHECKED_CAST!>as T<!>)
}
