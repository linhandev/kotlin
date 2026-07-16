// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, labels -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: continue@scan skips even i in scan@ for loop
 */

// TESTCASE NUMBER: 1
fun case1(limit: Int): Int {
    var sum = 0
    scan@ for (i in 1..limit) {
        if (i % 2 == 0) continue@scan
        sum += i
    }
    return sum
}
