// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 2 -> sentence 2
 *                type-system, type-kinds, type-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: is-check against List<*> is a valid star-projection runtime type test type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any) {
    checkSubtype<Int>(if (x is List<*>) x.size else -1)
}
