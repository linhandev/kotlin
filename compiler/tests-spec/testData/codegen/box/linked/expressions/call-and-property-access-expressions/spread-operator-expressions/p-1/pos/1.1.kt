// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, spread-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: spread operator passes array elements as vararg arguments
 */

// TESTCASE NUMBER: 1

fun join(vararg parts: String): String = parts.joinToString("")

fun box(): String {
    val middle = arrayOf("b", "c")
    return if (join("a", *middle, "d") == "abcd") "OK" else "NOK"
}
