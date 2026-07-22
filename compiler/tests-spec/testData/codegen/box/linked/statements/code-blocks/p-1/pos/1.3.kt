// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: ({ 1; 2; 3 })() yields 3 as lambda last expression
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val value = ({ 1; 2; 3 })()
    return if (value == 3) "OK" else "NOK"
}
