// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: non-local return from also lambda exits enclosing function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: Int): Int {
    x.also { if (it != 0) return 7 }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1(1))
}
