// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: return transfers control to caller with function result
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return read()
    "NOK"
}

private fun read(): String = "OK"
