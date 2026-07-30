// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 30 -> sentence 30
 *                expressions, range-expressions -> paragraph 30 -> sentence 30
 *                type-system, introduction-1 -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable subject cannot use containment operator requiring non-nullable element type
 */

// TESTCASE NUMBER: 1
class R {
    operator fun contains(x: Int): Boolean = true
}

fun test(x: Int?): String = when (<!TYPE_MISMATCH!>x<!>) {
    <!TYPE_MISMATCH_IN_RANGE!>in<!> R() -> "inside"
    else -> "other"
}
