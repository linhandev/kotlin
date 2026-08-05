// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 38 -> sentence 38
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: in operator rejects List<Int> lhs when contains convention expects Int element, not a nested list
 */

// TESTCASE NUMBER: 1
class Box(private val items: List<Int>) {
    operator fun contains(x: Int): Boolean = x in items
}

fun case1() {
    val b: Boolean = <!TYPE_MISMATCH!>listOf(1)<!> in Box(listOf(1, 2))
}
