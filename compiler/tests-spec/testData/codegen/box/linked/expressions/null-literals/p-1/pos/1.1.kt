// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, null-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: null assigned to String? compares equal to null
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: String? = null
    return if (x == null) "OK" else "NOK"
}
