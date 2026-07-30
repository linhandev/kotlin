// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 49 -> sentence 49
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: non-local return skips subsequent statements after forEach
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): String {
    xs.forEach { return "early" }
    return "late"
}

fun case_1_check() {
    checkSubtype<String>(case_1(listOf(1)))
}
