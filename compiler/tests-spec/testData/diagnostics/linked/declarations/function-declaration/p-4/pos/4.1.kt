// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: named arguments reorder parameters, bind two parameters by name, and override a default parameter value
 */

// TESTCASE NUMBER: 1
fun bar(a: Int, b: Double, s: String): Double = a + b + s.toDouble()

fun useBar(): Double = bar(b = 42.0, a = 5, s = "13")

// TESTCASE NUMBER: 2
fun foo(x: Int, y: Int): Int = x + y

fun useFoo(): Int = foo(x = 1, y = 2)

// TESTCASE NUMBER: 3
fun withDefault(a: Int = 1, b: String = "x"): String = "$a:$b"

fun useDefault(): String = withDefault(b = "ok")
