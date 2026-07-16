// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, boolean-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: literal false used in negated if condition evaluates to false
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = false
    return if (!value) "OK" else "NOK"
}
