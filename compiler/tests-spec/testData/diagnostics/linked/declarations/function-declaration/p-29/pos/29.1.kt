// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: tail-recursive function with recursive call in tail position is valid
 */

// TESTCASE NUMBER: 1
tailrec fun factorialTC(i: Int, result: Int = 1): Int {
    if (i == 0) return result
    return factorialTC(i - 1, i * result)
}

// TESTCASE NUMBER: 2
tailrec fun sumRange(n: Int, acc: Int = 0): Int {
    if (n == 0) return acc
    return sumRange(n - 1, acc + n)
}
