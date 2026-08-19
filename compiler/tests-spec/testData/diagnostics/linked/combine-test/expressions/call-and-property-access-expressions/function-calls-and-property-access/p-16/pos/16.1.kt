// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 16 -> sentence 16
 *                expressions, jump-expressions, return-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: inline trailing lambda non-local return type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun <T> runIf(cond: Boolean, block: () -> T): T? = if (cond) block() else null

fun case1(): Int {
    runIf(true) { return 1; 2 }
    return 2
}

fun case2() {
    checkSubtype<Int>(case1())
}
