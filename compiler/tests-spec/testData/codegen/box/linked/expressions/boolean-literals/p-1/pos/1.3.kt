// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, boolean-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: class `true` with backticks is valid escaped identifier for boolean keyword
 */

// TESTCASE NUMBER: 1

class `true` {
    val flag = false
}

fun box(): String {
    val instance = `true`()
    return if (instance.flag == false) "OK" else "NOK"
}
