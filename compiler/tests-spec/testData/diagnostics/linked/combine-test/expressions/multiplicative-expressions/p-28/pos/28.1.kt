// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 28 -> sentence 28
 *                type-system, built-in-integer-types -> paragraph 28 -> sentence 28
 *                statements, assignments, operator-assignments -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun timesAssign(Long) type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Acc(var v: Long) {
    operator fun timesAssign(k: Long) {
        v *= k
    }
}

fun case_1(): Long {
    val a = Acc(2L)
    a *= 5L
    return a.v
}

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2(): Acc {
    val a = Acc(2L)
    a *= 5L
    return a
}

fun case_2_check() {
    checkSubtype<Acc>(case_2())
}
