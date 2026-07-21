// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: default parameter values used when arguments are omitted
 */

// TESTCASE NUMBER: 1
fun f(x: Int = 0): Int = x

fun useF(): Int = f()

// TESTCASE NUMBER: 2
fun bar(a: Int = 1, b: Double = 42.0, s: String = "Hello"): Double = a + b + s.toDouble()

fun useBar(): Double {
    bar()
    bar(2)
    return bar(2, s = "Me")
}
