// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: vararg function accepts zero and multiple arguments at runtime
 */

// TESTCASE NUMBER: 1
fun join(vararg xs: Int): String = xs.joinToString(",")

fun box(): String {
    return if (join() == "" && join(1, 2, 3) == "1,2,3") "OK" else "NOK"
}
