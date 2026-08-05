// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 28 -> sentence 28
 *                expressions, when-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: when expression branch with separator integer literal infers String result type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: Int): String = when (x) {
    1_000 -> "k"
    else -> "o"
}

fun case_1_check() {
    checkSubtype<String>(case_1(1_000))
}

fun case_2_check() {
    checkSubtype<String>(case_1(1000))
}

fun case_3_check() {
    checkSubtype<String>(case_1(999))
}
