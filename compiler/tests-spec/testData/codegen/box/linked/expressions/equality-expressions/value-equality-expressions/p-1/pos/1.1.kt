// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, value-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: value equality operators == and != compare by value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1
    val b = 1
    return if (a == b && a != 2) "OK" else "NOK"
}
