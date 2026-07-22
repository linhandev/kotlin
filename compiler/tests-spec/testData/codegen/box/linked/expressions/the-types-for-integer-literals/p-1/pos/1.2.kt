// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, the-types-for-integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: literal 42L assigned to Long has type Long
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Long = 42L
    return if (x is Long && x == 42L) "OK" else "NOK"
}
