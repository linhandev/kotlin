// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, boolean-literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: true and false assigned to Boolean differ and both have Boolean type
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val a: Boolean = true
    val b: Boolean = false
    return if (a is Boolean && b is Boolean && a != b) "OK" else "NOK"
}
