// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 52 -> sentence 52
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 52 -> sentence 52
 * NUMBER: 1
 * DESCRIPTION: non-local return from repeat exits enclosing function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Int {
    repeat(3) { if (it == 1) return 9 }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
