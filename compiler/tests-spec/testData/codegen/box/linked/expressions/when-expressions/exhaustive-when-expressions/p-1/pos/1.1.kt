// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: when (true) with true and false branches assigns 1 without else branch
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Int = when (true) {
        true -> 1
        false -> 2
    }
    return if (x == 1) "OK" else "NOK"
}
