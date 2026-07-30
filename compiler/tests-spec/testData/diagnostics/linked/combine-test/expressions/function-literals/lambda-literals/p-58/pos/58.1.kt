// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: labeled run lambda allows return@label from nested forEach
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Int = run label@ {
    listOf(1).forEach { return@label 2 }
    0
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
