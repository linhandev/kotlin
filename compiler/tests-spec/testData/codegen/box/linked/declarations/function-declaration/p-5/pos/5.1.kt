// WITH_STDLIB
// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: mixed positional and named arguments at runtime
 */

// TESTCASE NUMBER: 1
fun foo(x: Int, y: Int): Int = x + y

fun box(): String {
    return if (foo(1, y = 2) == 3) "OK" else "NOK"
}
