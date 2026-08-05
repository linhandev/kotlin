// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 1 -> sentence 1
 *                expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: as? List<*> with Elvis fallback still cannot refine with is List<String> due to erasure
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any): Boolean {
    val y = x as? List<*> ?: return false
    return y is <!CANNOT_CHECK_FOR_ERASED!>List<String><!>
}
