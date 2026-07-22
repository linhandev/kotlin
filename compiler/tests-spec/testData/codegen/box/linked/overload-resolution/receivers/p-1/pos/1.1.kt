/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: declared classifier is available as implicit this in member function
 */

class Receiver1101(val token: String) {
    fun read(): String = token
}

// TESTCASE NUMBER: 1
fun box(): String {
    val result = Receiver1101("OK").read()
    return if (result == "OK") "OK" else "NOK: $result"
}
