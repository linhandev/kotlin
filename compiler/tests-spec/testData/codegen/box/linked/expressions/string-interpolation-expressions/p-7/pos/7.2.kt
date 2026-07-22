// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: multiline string template allows whitespace around code inside interpolation
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = "ab"
    val s = """
        len=${x
            .length}
        """
    return if (s.contains("len=2")) "OK" else "NOK"
}
