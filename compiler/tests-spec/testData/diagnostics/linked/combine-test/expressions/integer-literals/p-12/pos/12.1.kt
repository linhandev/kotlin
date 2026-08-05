// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 12 -> sentence 12
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: explicit Long variable infers Long type from separator Long literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long {
    val x: Long = 1_000L
    return x
}

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2_check() {
    val x: Long = 1_000L
    checkSubtype<Long>(x)
}

fun case_3_check() {
    val x: Long = 1_000L
    val y: Long = 2_000L
    checkSubtype<Long>(x + y)
}
