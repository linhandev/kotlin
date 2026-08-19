// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 5 -> sentence 5
 *                type-inference, smart-casts -> paragraph 5 -> sentence 5
 *                type-system, type-kinds, union-types -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast and different branch types infer common supertype Number
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = 1
    checkSubtype<Number>(if (x is Int) x + 1 else 0.5)
}
