// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: inline function body is executed at the call-site with mapped arguments
 */

// TESTCASE NUMBER: 1
inline fun <T, R> lock(value: T, body: (T) -> R): R = body(value)

fun box(): String {
    val sum = lock(2) { it + 3 }
    val text = lock("inline") { it.uppercase() }
    return if (sum == 5 && text == "INLINE") "OK" else "NOK sum=$sum text=$text"
}
