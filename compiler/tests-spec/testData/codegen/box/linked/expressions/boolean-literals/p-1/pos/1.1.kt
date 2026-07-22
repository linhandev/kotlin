// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, boolean-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: literal true used in if condition evaluates to true
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = true
    return if (value) "OK" else "NOK"
}
