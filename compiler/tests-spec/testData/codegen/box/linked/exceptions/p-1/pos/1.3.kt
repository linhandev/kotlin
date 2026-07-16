/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: user-defined Exception subtype is caught by its specific catch clause before a general Exception handler
 */
// TESTCASE NUMBER: 1

class MyException161 : Exception("custom-161")

fun box(): String {
    val result = try {
        throw MyException161()
    } catch (_: MyException161) {
        "specific"
    } catch (_: Exception) {
        "general"
    }
    return if (result == "specific") "OK" else "NOK"
}
