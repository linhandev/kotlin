// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, the-types-for-integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: literal 2147483648 greater than Int.MAX_VALUE has type Long
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 2147483648
    return if (x is Long && x == 2147483648L) "OK" else "NOK"
}
