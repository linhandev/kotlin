// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 16 -> sentence 16
 *                expressions, jump-expressions, return-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: non-local return is allowed in trailing lambda of inline higher-order function
 */

// TESTCASE NUMBER: 1
inline fun <T> runIf(cond: Boolean, block: () -> T): T? = if (cond) block() else null

fun test(): Int {
    runIf(true) { return 1; 2 }
    return 2
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
