// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, throw-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: throw custom Exception subclass as exception type
 */

// TESTCASE NUMBER: 1

class MyException : Exception()

fun box(): String {
    return try {
        throw MyException()
    } catch (_: MyException) {
        "OK"
    }
}
