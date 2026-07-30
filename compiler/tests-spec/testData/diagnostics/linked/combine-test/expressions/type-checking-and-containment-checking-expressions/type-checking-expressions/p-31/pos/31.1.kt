// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: as? safe cast with non-runtime-available type parameter produces unchecked cast warning
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(value: Any?) {
    checkSubtype<T?>(value <!UNCHECKED_CAST!>as? T<!>)
}
