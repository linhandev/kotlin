/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: extension this parameter is available inside extension declaration
 */

fun String.prefix1101(): String = this + "!"

// TESTCASE NUMBER: 1
fun box(): String {
    val result = "OK".prefix1101()
    return if (result == "OK!") "OK" else "NOK: $result"
}
