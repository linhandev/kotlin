// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: parameterized type as a non-runtime-available type cannot be used in catch clause
 */

// TESTCASE NUMBER: 1
fun case_1(): String = try {
    ""
} catch (<!TYPE_MISMATCH!>e: List<String><!>) {
    ""
}
