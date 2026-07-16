// FIR_IDENTICAL
// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: positional arguments before named arguments in mixed calls
 */

// TESTCASE NUMBER: 1
fun foo(x: Int, y: Int): Int = x + y

fun useFoo(): Int = foo(1, y = 2)

// TESTCASE NUMBER: 2
fun bar(a: Int, b: String, c: Double) {}

fun useBar() {
    bar(1, b = "2", c = 3.0)
    bar(a = 1, "2", c = 3.0)
}
