// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, labels -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: break@outer at i == 4 yields sum 6 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    outer@ for (i in 1..5) {
        if (i == 4) break@outer
        sum += i
    }
    return if (sum == 6) "OK" else "NOK"
}
