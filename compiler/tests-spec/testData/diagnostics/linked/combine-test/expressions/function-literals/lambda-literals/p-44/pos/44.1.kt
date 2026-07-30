// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: anonymous function return exits only the anonymous function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Int {
    val f = fun(): Int { return 2 }
    f()
    return 1
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
