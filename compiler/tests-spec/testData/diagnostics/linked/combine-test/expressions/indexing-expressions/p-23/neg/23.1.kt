// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 23 -> sentence 23
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: Map key type mismatch reports TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR
 */

// TESTCASE NUMBER: 1
fun test(): Int? = <!TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR!>mapOf(1 to 2)["k"]<!>
