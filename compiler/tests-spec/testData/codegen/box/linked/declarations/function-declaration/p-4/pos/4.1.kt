// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: named arguments produce correct runtime values
 */

// TESTCASE NUMBER: 1
fun bar(a: Int, b: Double, s: String): Double = a + b + s.toDouble()

fun box(): String {
    val result = bar(b = 42.0, a = 5, s = "13")
    return if (result == 60.0) "OK" else "NOK $result"
}
