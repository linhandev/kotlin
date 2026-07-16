// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, labels -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: break@inner exits inner@ loop when j == 2 in nested outer@ loop
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    var result = 0
    outer@ for (i in 1..3) {
        inner@ for (j in 1..3) {
            if (j == 2) break@inner
            result += j
        }
    }
    return result
}
