// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: variable length parameter accepts any number of arguments
 */

// TESTCASE NUMBER: 1
fun sum(vararg xs: Int): Int = xs.sum()

fun useVararg() {
    sum()
    sum(1)
    sum(1, 2, 3)
}

// TESTCASE NUMBER: 2
fun prefix(first: Int, vararg rest: Int): Int = first + rest.sum()

fun usePrefix() {
    prefix(10, 1, 2)
}
