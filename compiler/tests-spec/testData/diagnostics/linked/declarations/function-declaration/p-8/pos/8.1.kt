// FIR_IDENTICAL
// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: function calls mixing positional and named arguments, including omitted defaults, compile successfully
 */

// TESTCASE NUMBER: 1
fun foo(a: Int, b: String, c: Double = 0.0): Double = a + b.length + c

fun validList(): Double {
    foo(1, b = "x")
    foo(1, "x", c = 2.0)
    return foo(a = 1, b = "x", c = 2.0)
}

// TESTCASE NUMBER: 2
fun withDefaults(x: Int = 1, y: Int = 2): Int = x + y

fun omitWithDefaults(): Int {
    withDefaults()
    return withDefaults(y = 5)
}
