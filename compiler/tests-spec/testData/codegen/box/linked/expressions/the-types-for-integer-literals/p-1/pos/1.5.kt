// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, the-types-for-integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: integer literal 70000 is assignable to Int and Long but not smaller types
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val asInt: Int = 70000
    val asLong: Long = 70000
    return if (asInt == 70000 && asLong == 70000L) "OK" else "NOK"
}
