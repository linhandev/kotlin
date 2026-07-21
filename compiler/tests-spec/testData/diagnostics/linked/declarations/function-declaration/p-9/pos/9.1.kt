// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: function call evaluates arguments left-to-right including side effects in parameter expressions
 */

// TESTCASE NUMBER: 1
var log = ""

fun <T> logged(value: T): T = value.also { log += "$value," }

fun combine(a: Int, b: Int, c: Int): Int = a + b + c

fun leftToRightOrder(): Boolean {
    log = ""
    val result = combine(logged(1), logged(2), logged(3))
    return result == 6 && log == "1,2,3,"
}

// TESTCASE NUMBER: 2
fun pair(first: String, second: String): String = first + second

fun namedOrder(): Boolean {
    log = ""
    val result = pair(second = logged("b"), first = logged("a"))
    return result == "ab" && log == "a,b,"
}
