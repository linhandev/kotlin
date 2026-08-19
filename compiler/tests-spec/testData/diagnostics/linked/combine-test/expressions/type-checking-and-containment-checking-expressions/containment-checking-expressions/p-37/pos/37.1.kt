// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 37 -> sentence 37
 *                type-system, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: not-in operator with nullable Int? element on List<Int?> infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val xs: List<Int?> = listOf(1, 2)
    checkSubtype<Boolean>(null !in xs)
    checkSubtype<Boolean>(1 !in xs)
    checkSubtype<Boolean>(3 !in xs)
}
