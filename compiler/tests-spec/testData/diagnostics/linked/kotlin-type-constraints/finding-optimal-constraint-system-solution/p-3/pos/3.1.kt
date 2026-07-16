// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, finding-optimal-constraint-system-solution -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: staged resolution resolves independent inference variables T then R
 * HELPERS: checkType
 */

fun <T, R> apply1322(value: T, transform: (T) -> R): R = transform(value)

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<String>(apply1322(1) { it.toString() })
}
