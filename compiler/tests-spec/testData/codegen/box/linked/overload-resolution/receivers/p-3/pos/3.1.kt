/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: labeled this-expression accesses non-default implicit receiver in nested scope
 */

class Outer1103(val token: String) {
    inner class Inner {
        fun read(): String {
            return this@Outer1103.token
        }
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val result = Outer1103("OK").Inner().read()
    return if (result == "OK") "OK" else "NOK: $result"
}
