// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, integer-type-widening -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: integer literal selects kotlin.Int overload at runtime
 */
// TESTCASE NUMBER: 1

fun foo(value: Int) = 1
fun foo(value: Short) = 2

fun box(): String {
    return if (foo(2) == 1) "OK" else "NOK"
}
