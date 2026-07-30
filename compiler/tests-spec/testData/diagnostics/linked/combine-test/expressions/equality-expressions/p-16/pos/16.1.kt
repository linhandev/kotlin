// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: == and === on buildString instances infer Boolean separately
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun s(x: String): String = buildString { append(x) }

fun case1() {
    val a = s("a")
    val b = s("a")
    checkSubtype<Boolean>(a == b)
    checkSubtype<Boolean>(a === b)
    checkSubtype<Boolean>(a === a)
}
